/*
 * Designed and developed by 2024 androidpoet (Ranbir Singh)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.androidpoet.dropdown

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowLeft
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.draw.alpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LocalItemContentPadding = staticCompositionLocalOf<PaddingValues?> { null }
private val LocalCompactMode = staticCompositionLocalOf { false }

/**
 * [Dropdown] is a DropdownMenu wrapper class to add cascade effect and animations.
 *
 * @param modifier The modifier for the Dropdown.
 * @param isOpen Represents whether the dropdown is open.
 * @param menu Represents the menu items to be displayed in the dropdown.
 * @param colors Specifies the colors for the dropdown menu.
 * @param offset Defines the offset of the dropdown from its default position.
 * @param placement Preferred placement relative to the anchor. Default is Down.
 * @param enter The enter animation for the dropdown content.
 * @param exit The exit animation for the dropdown content.
 * @param easing The easing function for the animation.
 * @param enterDuration The duration for the enter animation.
 * @param exitDuration The duration for the exit animation.
 * @param searchable If true, shows a search field at the top of the dropdown.
 * @param searchPlaceholder Placeholder text for the search field.
 * @param compact If true, uses reduced padding for menu items.
 * @param contentPadding Custom padding values for each menu item.
 * @param onItemSelected Callback triggered when an item is selected in the dropdown.
 * @param onDismiss Callback executed when the dropdown is dismissed or closed.
 */

@Composable
public fun <T : Any> Dropdown(
  modifier: Modifier = Modifier,
  isOpen: Boolean,
  menu: MenuItem<T>,
  colors: DropDownMenuColors = dropDownMenuColors(),
  offset: DpOffset = DpOffset.Zero,
  placement: MenuPlacement = MenuPlacement.Down,
  enter: EnterAnimation = EnterAnimation.FadeIn,
  exit: ExitAnimation = ExitAnimation.FadeOut,
  easing: Easing = Easing.FastOutLinearInEasing,
  enterDuration: Int = 500,
  exitDuration: Int = 500,
  width: Dp = MAX_WIDTH,
  searchable: Boolean = false,
  searchPlaceholder: String = "Search",
  compact: Boolean = false,
  contentPadding: PaddingValues? = null,
  onItemSelected: (T?) -> Unit,
  onDismiss: () -> Unit,
) {
  var currentMenu by remember(menu, isOpen) { mutableStateOf(menu) }
  var searchQuery by remember { mutableStateOf("") }
  var focusedIndex by remember { mutableStateOf(0) }

  val finalModifier = modifier.width(width).background(colors.backgroundColor)

  val resolvedOffset = when (placement) {
    MenuPlacement.Down -> offset
    MenuPlacement.Up -> DpOffset(offset.x, -width - offset.y)
    MenuPlacement.End -> {
      val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
      DpOffset(if (isRtl) -width - offset.x else offset.x, offset.y)
    }
    MenuPlacement.Start -> {
      val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
      DpOffset(if (isRtl) offset.x else -width - offset.x, offset.y)
    }
    MenuPlacement.Auto -> offset // Simple auto: just use offset (could enhance with BoxWithConstraints)
  }

  DropdownMenu(
    modifier = finalModifier,
    expanded = isOpen,
    onDismissRequest = { onDismiss() },
    offset = resolvedOffset,
  ) {
    CompositionLocalProvider(
      LocalCompactMode provides compact,
      LocalItemContentPadding provides contentPadding,
    ) {
      if (searchable) {
        SearchHeader(
          query = searchQuery,
          onQueryChange = { searchQuery = it },
          placeholder = searchPlaceholder,
          colors = colors,
        )
      }
      AnimatedContent(targetState = currentMenu, transitionSpec = {
        animateContent(
          AnimationProp(
            enterDuration = enterDuration,
            exitDuration = exitDuration,
            delay = 0,
            easing = easing,
          ),
          enterAnimation = enter,
          exitAnimation = exit,
        )
      }) { targetMenu ->
        DropdownContent(
          targetMenu = targetMenu,
          searchQuery = searchQuery,
          onItemSelected = onItemSelected,
          colors = colors,
          width = width,
          focusedIndex = focusedIndex,
          onFocusedIndexChange = { focusedIndex = it },
          onParentClick = {
            focusedIndex = 0
            currentMenu =
              targetMenu.parent ?: throw IllegalStateException("Invalid parent menu")
          },
          onChildClick = { id ->
            searchQuery = ""
            focusedIndex = 0
            val child = targetMenu.getChild(id)
            currentMenu = child ?: throw IllegalStateException("Invalid item id: $id")
          },
        )
      }
    }
  }
}

/**
 * A search header composable for filtering dropdown items.
 */
@Composable
internal fun SearchHeader(
  query: String,
  onQueryChange: (String) -> Unit,
  placeholder: String,
  colors: DropDownMenuColors,
) {
  OutlinedTextField(
    value = query,
    onValueChange = onQueryChange,
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp, vertical = 4.dp),
    placeholder = { Text(placeholder) },
    leadingIcon = {
      Icon(Icons.Filled.Search, contentDescription = null, tint = colors.contentColor)
    },
    singleLine = true,
    shape = RoundedCornerShape(8.dp),
    colors = OutlinedTextFieldDefaults.colors(
      focusedTextColor = colors.contentColor,
      unfocusedTextColor = colors.contentColor,
      cursorColor = colors.contentColor,
      focusedBorderColor = colors.contentColor.copy(alpha = 0.5f),
      unfocusedBorderColor = colors.contentColor.copy(alpha = 0.2f),
    ),
  )
}

/**
 * Represents menu content properties for a dropdown menu.
 *
 * @param targetMenu The MenuItem representing the dropdown menu.
 * @param searchQuery The current search query for filtering items.
 * @param onItemSelected Callback for when an item is selected.
 * @param colors Dropdown menu colors.
 * @param onParentClick Callback for when the parent item is clicked.
 * @param onChildClick Callback for when a child item is clicked.
 * @param compact If true, uses reduced padding for items.
 * @param contentPadding Custom padding for each menu item.
 */

@Composable
public fun <T : Any> DropdownContent(
  targetMenu: MenuItem<T>,
  searchQuery: String = "",
  onItemSelected: (T?) -> Unit,
  colors: DropDownMenuColors,
  onParentClick: () -> Unit,
  width: Dp,
  onChildClick: (T) -> Unit,
  focusedIndex: Int = 0,
  onFocusedIndexChange: (Int) -> Unit = {},
) {
  val items = remember(targetMenu) {
    targetMenu.children?.filterIsInstance<MenuItem<T>>().orEmpty()
  }
  Column(
    modifier = Modifier
      .width(width)
      .onKeyEvent { event ->
        when (event.key) {
          Key.DirectionDown -> {
            if (focusedIndex < items.size - 1) {
              onFocusedIndexChange(focusedIndex + 1)
            }
            true
          }
          Key.DirectionUp -> {
            if (focusedIndex > 0) {
              onFocusedIndexChange(focusedIndex - 1)
            }
            true
          }
          Key.Enter -> {
            if (items.isNotEmpty() && focusedIndex < items.size) {
              val item = items[focusedIndex]
              if (item.hasChildren()) {
                onChildClick(item.id ?: return@onKeyEvent true)
              } else {
                onItemSelected(item.id)
              }
            }
            true
          }
          Key.DirectionRight -> {
            if (items.isNotEmpty() && focusedIndex < items.size) {
              val item = items[focusedIndex]
              if (item.hasChildren()) {
                onChildClick(item.id ?: return@onKeyEvent true)
              }
            }
            true
          }
          Key.DirectionLeft -> {
            if (targetMenu.hasParent()) {
              onParentClick()
            }
            true
          }
          else -> false
        }
      },
  ) {
    if (targetMenu.hasParent()) {
      CascadeHeaderItem(
        targetMenu.title,
        colors.contentColor,
        onClick = onParentClick,
      )
    }
    if (targetMenu.hasChildren()) {
      val visibleItems = if (searchQuery.isBlank()) {
        targetMenu.children
      } else {
        targetMenu.children?.filter { item ->
          when (item) {
            is MenuItem -> item.title.contains(searchQuery, ignoreCase = true)
            else -> true
          }
        }
      }
      visibleItems?.forEach { menuItem ->
        when (menuItem) {
          is MenuItem -> {
            if (menuItem.hasChildren()) {
              ParentItem(
                id = menuItem.id,
                title = menuItem.title,
                subtitle = menuItem.subtitle,
                badge = menuItem.badge,
                icon = menuItem.icon,
                selectable = menuItem.selectable,
                selected = menuItem.selected,
                contentColor = colors.contentColor,
                customContent = menuItem.customContent,
                onClick = { id ->
                  if (id != null) {
                    onChildClick(id)
                  }
                },
              )
            } else {
              ChildItem(
                id = menuItem.id,
                title = menuItem.title,
                subtitle = menuItem.subtitle,
                badge = menuItem.badge,
                icon = menuItem.icon,
                selectable = menuItem.selectable,
                selected = menuItem.selected,
                contentColor = colors.contentColor,
                customContent = menuItem.customContent,
                onClick = onItemSelected,
              )
            }
          }

          is SectionHeaderItem -> {
            SectionHeader(
              title = menuItem.title,
              contentColor = colors.contentColor,
            )
          }

          is Divider -> {
            HorizontalDivider(
              modifier = Modifier.fillMaxWidth(),
              color = colors.contentColor.copy(alpha = 0.12f),
            )
          }
        }
      }
    }
  }
}

/**
 * Displays a section header label within the dropdown menu.
 *
 * @param title The title text for the section.
 * @param contentColor The color of the content.
 */
@Composable
public fun SectionHeader(
  title: String,
  contentColor: Color,
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 12.dp, vertical = 6.dp),
    text = title.uppercase(),
    style = MaterialTheme.typography.labelSmall,
    fontWeight = FontWeight.SemiBold,
    color = contentColor.copy(alpha = ContentAlpha.MEDIUM),
    maxLines = 1,
  )
}

/**
 * Adds space content.
 */
@Composable
public fun Space() {
  Spacer(modifier = Modifier.width(12.dp))
}

/**
 * Displays an icon for the MenuItem.
 *
 * @param icon The ImageVector representing the icon.
 * @param tint The color to tint the icon.
 */
@Composable
public fun MenuItemIcon(
  icon: ImageVector,
  tint: Color,
) {
  Icon(
    modifier = Modifier.size(24.dp),
    imageVector = icon,
    contentDescription = null,
    tint = tint,
  )
}

/**
 * Displays text for the MenuItem.
 *
 * @param modifier Modifier for styling.
 * @param text The text to display.
 * @param color The color of the text.
 * @param isHeaderText Flag indicating if the text is header text.
 */
@Composable
public fun MenuItemText(
  modifier: Modifier,
  text: String,
  color: Color,
  isHeaderText: Boolean = false,
) {
  val style =
    if (isHeaderText) {
      MaterialTheme.typography.bodyMedium
    } else {
      MaterialTheme.typography.bodySmall
    }

  Text(
    modifier = modifier,
    text = text,
    style = style,
    color = color,
  )
}

/**
 * Displays a numeric badge indicator for a MenuItem.
 *
 * @param count The number to display in the badge.
 * @param tint The color of the badge text and background.
 */
@Composable
public fun MenuItemBadge(
  count: Int,
  tint: Color,
) {
  val badgeText = if (count > 99) "99+" else count.toString()
  Box(
    modifier = Modifier
      .size(20.dp)
      .background(
        color = tint.copy(alpha = 0.15f),
        shape = CircleShape,
      ),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = badgeText,
      style = MaterialTheme.typography.labelSmall,
      fontSize = 10.sp,
      fontWeight = FontWeight.Bold,
      color = tint,
      textAlign = TextAlign.Center,
    )
  }
}

/**
 * Wrapper for handling onClick and user interaction for a dropdown MenuItem.
 *
 * @param onClick Callback for when the MenuItem is clicked.
 * @param enabled Whether this item is enabled (grayed out and non-interactive when false).
 * @param content The content to be displayed within the MenuItem.
 */
@Composable
public fun MenuItem(
  onClick: () -> Unit,
  enabled: Boolean = true,
  content: @Composable RowScope.() -> Unit,
) {
  val compact = LocalCompactMode.current
  val contentPadding = LocalItemContentPadding.current
  val padding = contentPadding ?: if (compact) {
    PaddingValues(horizontal = 8.dp, vertical = 4.dp)
  } else {
    PaddingValues(horizontal = 12.dp, vertical = 8.dp)
  }
  DropdownMenuItem(
    onClick = onClick,
    contentPadding = padding,
    interactionSource = remember { MutableInteractionSource() },
    text = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        content()
      }
    },
  )
}

/**
 * Represents a header item of the menu.
 *
 * @param title The title of the header item.
 * @param contentColor The color of the content.
 * @param onClick Callback for when the header item is clicked.
 */
@Composable
public fun CascadeHeaderItem(
  title: String,
  contentColor: Color,
  onClick: () -> Unit,
) {
  val layoutDirection = LocalLayoutDirection.current
  val backIcon = if (layoutDirection == LayoutDirection.Ltr) {
    Icons.AutoMirrored.Rounded.ArrowLeft
  } else {
    Icons.AutoMirrored.Rounded.ArrowRight
  }
  MenuItem(onClick = { onClick() }) {
    MenuItemIcon(
      icon = backIcon,
      tint = contentColor.copy(alpha = ContentAlpha.MEDIUM),
    )
    Spacer(modifier = Modifier.width(4.dp))
    MenuItemText(
      modifier = Modifier.weight(1f),
      text = title,
      color = contentColor.copy(alpha = ContentAlpha.MEDIUM),
      isHeaderText = true,
    )
  }
}

@Composable
private fun selectableIcon(
  selectable: SelectMode,
  selected: Boolean,
  tint: Color,
): ImageVector? = when {
  selectable == SelectMode.Multi && selected -> Icons.Filled.CheckBox
  selectable == SelectMode.Multi && !selected -> Icons.Filled.CheckBoxOutlineBlank
  selectable == SelectMode.Single && selected -> Icons.Filled.RadioButtonChecked
  selectable == SelectMode.Single && !selected -> Icons.Filled.RadioButtonUnchecked
  else -> null
}

/**
 * Represents a parent item of the menu.
 *
 * @param id The ID of the parent item.
 * @param title The title of the parent item.
 * @param subtitle Optional secondary text displayed below the title.
 * @param icon The icon for the parent item.
 * @param selectable The selection mode for this item.
 * @param selected Whether this item is currently selected.
 * @param contentColor The color of the content.
 * @param customContent Optional custom composable content for this item.
 * @param onClick Callback for when the parent item is clicked.
 */
@Composable
public fun <T> ParentItem(
  id: T,
  title: String,
  subtitle: String? = null,
  badge: Int? = null,
  icon: ImageVector?,
  selectable: SelectMode = SelectMode.None,
  selected: Boolean = false,
  contentColor: Color,
  customContent: (@Composable RowScope.() -> Unit)? = null,
  onClick: (T) -> Unit,
) {
  val forwardIcon = Icons.AutoMirrored.Rounded.ArrowRight
  MenuItem(onClick = { onClick(id) }) {
    if (customContent != null) {
      customContent()
    } else {
      if (selectable != SelectMode.None) {
        val selIcon = selectableIcon(selectable, selected, contentColor)
        if (selIcon != null) {
          MenuItemIcon(icon = selIcon, tint = contentColor)
          Space()
        }
      }
      if (icon != null) {
        MenuItemIcon(icon = icon, tint = contentColor)
        Space()
      }
      Column(modifier = Modifier.weight(1f)) {
        MenuItemText(
          modifier = Modifier,
          text = title,
          color = contentColor,
        )
        if (subtitle != null) {
          Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = contentColor.copy(alpha = ContentAlpha.MEDIUM),
          )
        }
      }
      if (badge != null) {
        Space()
        MenuItemBadge(count = badge, tint = contentColor)
      }
      Space()
      MenuItemIcon(icon = forwardIcon, tint = contentColor)
    }
  }
}

/**
 * Represents a child item of the menu.
 *
 * @param id The ID of the child item.
 * @param title The title of the child item.
 * @param subtitle Optional secondary text displayed below the title.
 * @param icon The icon for the child item.
 * @param selectable The selection mode for this item.
 * @param selected Whether this item is currently selected.
 * @param contentColor The color of the content.
 * @param customContent Optional custom composable content for this item.
 * @param onClick Callback for when the child item is clicked.
 */
@Composable
public fun <T> ChildItem(
  id: T,
  title: String,
  subtitle: String? = null,
  badge: Int? = null,
  icon: ImageVector?,
  selectable: SelectMode = SelectMode.None,
  selected: Boolean = false,
  contentColor: Color,
  customContent: (@Composable RowScope.() -> Unit)? = null,
  onClick: (T) -> Unit,
) {
  MenuItem(onClick = { onClick(id) }) {
    if (customContent != null) {
      customContent()
    } else {
      if (selectable != SelectMode.None) {
        val selIcon = selectableIcon(selectable, selected, contentColor)
        if (selIcon != null) {
          MenuItemIcon(icon = selIcon, tint = contentColor)
          Space()
        }
      }
      if (icon != null) {
        MenuItemIcon(icon = icon, tint = contentColor)
        Space()
      }
      Column(modifier = Modifier.weight(1f)) {
        MenuItemText(
          modifier = Modifier,
          text = title,
          color = contentColor,
        )
        if (subtitle != null) {
          Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = contentColor.copy(alpha = ContentAlpha.MEDIUM),
          )
        }
      }
      if (badge != null) {
        Space()
        MenuItemBadge(count = badge, tint = contentColor)
      }
    }
  }
}

// The internal function remains unchanged
internal fun animateContent(
  animationProp: AnimationProp,
  enterAnimation: EnterAnimation,
  exitAnimation: ExitAnimation,
): ContentTransform =
  getEnterAnimation(animationProp, enterAnimation) togetherWith
    getExitAnimation(
      animationProp,
      exitAnimation,
    )

public val MAX_WIDTH: Dp = 192.dp

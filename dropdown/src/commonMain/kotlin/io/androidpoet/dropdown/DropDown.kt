/*
 * Designed and developed by 2022 androidpoet (Ranbir Singh)
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowLeft
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * [Dropdown] is a DropdownMenu wrapper class to add cascade effect and animations.
 *
 * @param modifier The modifier for the Dropdown.
 * @param isOpen Represents whether the dropdown is open.
 * @param menu Represents the menu items to be displayed in the dropdown.
 * @param colors Specifies the colors for the dropdown menu.
 * @param offset Defines the offset of the dropdown from its default position.
 * @param enter The enter animation for the dropdown content.
 * @param exit The exit animation for the dropdown content.
 * @param easing The easing function for the animation.
 * @param enterDuration The duration for the enter animation.
 * @param exitDuration The duration for the exit animation.
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
  enter: EnterAnimation = EnterAnimation.FadeIn,
  exit: ExitAnimation = ExitAnimation.FadeOut,
  easing: Easing = Easing.FastOutLinearInEasing,
  enterDuration: Int = 500,
  exitDuration: Int = 500,
  onItemSelected: (T?) -> Unit,
  onDismiss: () -> Unit,
) {
  var currentMenu by remember(menu, isOpen) { mutableStateOf(menu) }

  DropdownMenu(
    modifier = modifier.width(MAX_WIDTH).background(colors.backgroundColor),
    expanded = isOpen,
    onDismissRequest = { onDismiss() },
    offset = offset,
  ) {
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
        onItemSelected = onItemSelected,
        colors = colors,
        onParentClick = {
          currentMenu =
            targetMenu.parent ?: throw IllegalStateException("Invalid parent menu")
        },
        onChildClick = { id ->
          val child = targetMenu.getChild(id)
          currentMenu = child ?: throw IllegalStateException("Invalid item id: $id")
        },
      )
    }
  }
}

/**
 * Represents menu content properties for a dropdown menu.
 *
 * @param targetMenu The MenuItem representing the dropdown menu.
 * @param onItemSelected Callback for when an item is selected.
 * @param colors Dropdown menu colors.
 * @param onParentClick Callback for when the parent item is clicked.
 * @param onChildClick Callback for when a child item is clicked.
 */

@Composable
public fun <T : Any> DropdownContent(
  targetMenu: MenuItem<T>,
  onItemSelected: (T?) -> Unit,
  colors: DropDownMenuColors,
  onParentClick: () -> Unit,
  onChildClick: (T) -> Unit,
) {
  Column(modifier = Modifier.width(192.dp)) {
    if (targetMenu.hasParent()) {
      CascadeHeaderItem(
        targetMenu.title,
        colors.contentColor,
        onClick = onParentClick,
      )
    }
    if (targetMenu.hasChildren()) {
      targetMenu.children?.forEach { item ->
        if (item.hasChildren()) {
          ParentItem(
            item.id,
            item.title,
            item.icon,
            colors.contentColor,
            onClick = { id ->
              if (id != null) {
                onChildClick(id)
              }
            },
          )
        } else {
          ChildItem(
            item.id,
            item.title,
            item.icon,
            colors.contentColor,
            onItemSelected,
          )
        }
      }
    }
  }
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
 * Wrapper for handling onClick and user interaction for a dropdown MenuItem.
 *
 * @param onClick Callback for when the MenuItem is clicked.
 * @param content The content to be displayed within the MenuItem.
 */
@Composable
public fun MenuItem(
  onClick: () -> Unit,
  content: @Composable RowScope.() -> Unit,
) {
  DropdownMenuItem(
    onClick = onClick,
    interactionSource = remember { MutableInteractionSource() },
    text = {
      Row {
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
  MenuItem(onClick = { onClick() }) {
    MenuItemIcon(
      icon = Icons.AutoMirrored.Rounded.ArrowLeft,
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

/**
 * Represents a parent item of the menu.
 *
 * @param id The ID of the parent item.
 * @param title The title of the parent item.
 * @param icon The icon for the parent item.
 * @param contentColor The color of the content.
 * @param onClick Callback for when the parent item is clicked.
 */
@Composable
public fun <T> ParentItem(
  id: T,
  title: String,
  icon: ImageVector?,
  contentColor: Color,
  onClick: (T) -> Unit,
) {
  MenuItem(onClick = { onClick(id) }) {
    if (icon != null) {
      MenuItemIcon(icon = icon, tint = contentColor)
      Space()
    }
    MenuItemText(
      modifier = Modifier.weight(1f),
      text = title,
      color = contentColor,
    )
    Space()
    MenuItemIcon(icon = Icons.AutoMirrored.Rounded.ArrowRight, tint = contentColor)
  }
}

/**
 * Represents a child item of the menu.
 *
 * @param id The ID of the child item.
 * @param title The title of the child item.
 * @param icon The icon for the child item.
 * @param contentColor The color of the content.
 * @param onClick Callback for when the child item is clicked.
 */
@Composable
public fun <T> ChildItem(
  id: T,
  title: String,
  icon: ImageVector?,
  contentColor: Color,
  onClick: (T) -> Unit,
) {
  MenuItem(onClick = { onClick(id) }) {
    if (icon != null) {
      MenuItemIcon(icon = icon, tint = contentColor)
      Space()
    }
    MenuItemText(
      modifier = Modifier.weight(1f),
      text = title,
      color = contentColor,
    )
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

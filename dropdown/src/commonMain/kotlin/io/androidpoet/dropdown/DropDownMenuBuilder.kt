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

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines the preferred placement of the dropdown menu relative to its anchor.
 */
public enum class MenuPlacement {
  /** Automatically detect best placement based on available space. */
  Auto,
  /** Place below the anchor (default). */
  Down,
  /** Place above the anchor. */
  Up,
  /** Place to the end (right in LTR, left in RTL). */
  End,
  /** Place to the start (left in LTR, right in RTL). */
  Start,
}

@DslMarker
public annotation class MenuDSL

// Create a sealed interface for menu items
@MenuDSL
public sealed interface IMenuItem<T : Any> {
  public val parent: MenuItem<T>?
}

// Represent a divider as a special menu item
@MenuDSL
public class Divider<T : Any>(override val parent: MenuItem<T>? = null) : IMenuItem<T>

// Represent a section header as a special menu item
@MenuDSL
public class SectionHeaderItem<T : Any>(
  public val title: String,
  override val parent: MenuItem<T>? = null,
) : IMenuItem<T>

@MenuDSL
public data class MenuItem<T : Any>(
  val id: T? = null,
  val title: String = "",
  override val parent: MenuItem<T>? = null,
  val enabled: Boolean = true,
) : IMenuItem<T> {
  var icon: ImageVector? = null
  var selectable: SelectMode = SelectMode.None
  var selected: Boolean = false
  var children: MutableList<IMenuItem<T>>? = null // Note: Changed to IMenuItem

  public fun hasChildren(): Boolean = !children.isNullOrEmpty()

  public fun hasParent(): Boolean = parent != null

  public fun getChild(id: T): MenuItem<T>? =
    children?.filterIsInstance<MenuItem<T>>()?.find { item -> item.id == id }
}

@MenuDSL
public class DropDownMenuBuilder<T : Any> {
  public var menu: MenuItem<T> = MenuItem()

  public fun icon(value: ImageVector) {
    menu.icon = value
  }

  public fun selectable(mode: SelectMode, selected: Boolean = false) {
    menu.selectable = mode
    menu.selected = selected
  }

  public fun horizontalDivider() {
    if (menu.children == null) {
      menu.children = mutableListOf()
    }
    menu.children?.add(Divider(menu))
  }

  public fun section(title: String) {
    if (menu.children == null) {
      menu.children = mutableListOf()
    }
    menu.children?.add(SectionHeaderItem(title, menu))
  }

  public fun item(
    id: T,
    title: String,
    enabled: Boolean = true,
    init: (DropDownMenuBuilder<T>.() -> Unit)? = null,
  ) {
    val newItem = MenuItem<T>(id, title, menu, enabled)

    init?.invoke(DropDownMenuBuilder<T>().apply { menu = newItem })

    if (menu.children == null) {
      menu.children = mutableListOf()
    }

    menu.children?.add(newItem)
  }
}

public fun <T : Any> dropDownMenu(init: DropDownMenuBuilder<T>.() -> Unit): MenuItem<T> {
  val menuBuilder = DropDownMenuBuilder<T>()
  menuBuilder.init()
  return menuBuilder.menu
}

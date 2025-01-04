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

import androidx.compose.ui.graphics.vector.ImageVector

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

@MenuDSL
public data class MenuItem<T : Any>(
  val id: T? = null,
  val title: String = "",
  override val parent: MenuItem<T>? = null,
) : IMenuItem<T> {
  var icon: ImageVector? = null
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

  public fun horizontalDivider() {
    if (menu.children == null) {
      menu.children = mutableListOf()
    }
    menu.children?.add(Divider(menu))
  }

  public fun item(
    id: T,
    title: String,
    init: (DropDownMenuBuilder<T>.() -> Unit)? = null,
  ) {
    val newItem = MenuItem<T>(id, title, menu)

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

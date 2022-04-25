
package com.androidpoet.dropdown

import androidx.compose.ui.graphics.vector.ImageVector

/** DropDownMenuBuilder to build the menu. */

public class DropDownMenuBuilder<T : Any> {
  public var menu: MenuItem<T> = MenuItem<T>()

  public fun icon(value: ImageVector) {
    menu.icon = value
  }

  public fun item(id: T, title: String, init: (DropDownMenuBuilder<T>.() -> Unit)? = null) {
    val menuBuilder = DropDownMenuBuilder<T>()
    val child = menuBuilder.menu.apply {
      this.id = id
      this.title = title
    }
    init?.let {
      menuBuilder.init()
    }
    menu.children = menu.children ?: mutableListOf()
    child.parent = menu
    menu.children!!.add(child)
  }
}

public fun <T : Any> dropDownMenu(init: DropDownMenuBuilder<T>.() -> Unit): MenuItem<T> {
  val menuBuilder = DropDownMenuBuilder<T>()
  menuBuilder.init()
  return menuBuilder.menu
}

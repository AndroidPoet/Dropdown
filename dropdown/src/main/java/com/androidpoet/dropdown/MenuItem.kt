
package com.androidpoet.dropdown

import androidx.compose.ui.graphics.vector.ImageVector

/*[MenuItem]  generic menu item with title and icon. */
public class MenuItem<T : Any> {
  public lateinit var id: T

  public lateinit var title: String

  public var icon: ImageVector? = null

  public var parent: MenuItem<T>? = null

  public var children: MutableList<MenuItem<T>>? = null

  public fun hasChildren(): Boolean = !children.isNullOrEmpty()

  public fun hasParent(): Boolean = parent != null

  public fun getChild(id: T): MenuItem<T>? = children?.let { items -> items.find { item -> item.id == id } }
}

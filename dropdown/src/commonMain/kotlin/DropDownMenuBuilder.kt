import androidx.compose.ui.graphics.vector.ImageVector

@DslMarker
annotation class MenuDSL

@MenuDSL
data class MenuItem<T : Any>(val id: T? = null, val title: String = "") {
    var icon: ImageVector? = null
    var children: MutableList<MenuItem<T>>? = null
    var parent: MenuItem<T>? = null

    public fun hasChildren(): Boolean = !children.isNullOrEmpty()

    public fun hasParent(): Boolean = parent != null

    public fun getChild(id: T): MenuItem<T>? =
        children?.let { items -> items.find { item -> item.id == id } }
}

@MenuDSL
class DropDownMenuBuilder<T : Any> {
    var menu: MenuItem<T> = MenuItem()

    fun icon(value: ImageVector) {
        menu.icon = value
    }

    fun item(
        id: T,
        title: String,
        init: (DropDownMenuBuilder<T>.() -> Unit)? = null,
    ) {
        val newItem = MenuItem<T>(id, title)
        newItem.parent = menu

        init?.invoke(DropDownMenuBuilder<T>().apply { menu = newItem })

        if (menu.children == null) {
            menu.children = mutableListOf()
        }

        menu.children?.add(newItem)
    }
}

fun <T : Any> dropDownMenu(init: DropDownMenuBuilder<T>.() -> Unit): MenuItem<T> {
    val menuBuilder = DropDownMenuBuilder<T>()
    menuBuilder.init()
    return menuBuilder.menu
}

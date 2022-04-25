
package com.androidpoet.dropdown

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/*[MenuState]  represents currentMenu state. */
public class MenuState<T : Any>(currentMenuItem: MenuItem<T>) {
  private var _currentMenu by mutableStateOf(currentMenuItem)

  public var currentMenuItem: MenuItem<T>
    get() = _currentMenu
    set(value) {
      _currentMenu = value
    }
}

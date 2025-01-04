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
package io.androidpoet.dropdown.baselineprofile.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import io.androidpoet.dropdown.Dropdown
import io.androidpoet.dropdown.Easing
import io.androidpoet.dropdown.EnterAnimation
import io.androidpoet.dropdown.ExitAnimation
import io.androidpoet.dropdown.MenuItem
import io.androidpoet.dropdown.baselineprofile.app.ui.theme.DropdownDemoTheme
import io.androidpoet.dropdown.dropDownMenu
import io.androidpoet.dropdown.dropDownMenuColors

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      DropdownDemoTheme {
        var isOpen by remember { mutableStateOf(false) }

        Column(
          Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Box(contentAlignment = Alignment.Center) {
            IconButton(onClick = { isOpen = true }) {
              Icon(
                imageVector = Icons.TwoTone.MoreVert,
                contentDescription = null,
                tint = Color.Black,
              )
            }

            val menu = getMenu()
            Dropdown(
              isOpen = isOpen,
              menu = menu,
              colors = dropDownMenuColors(Color(0xFF01D0BC), Color.Black),
              onItemSelected = {},
              onDismiss = { isOpen = false },
              offset = DpOffset(8.dp, 0.dp),
              enter = EnterAnimation.SlideInHorizontally,
              exit = ExitAnimation.SlideOutHorizontally,
              easing = Easing.LinearOutSlowInEasing,
              enterDuration = 400,
              exitDuration = 400,
            )
          }
        }
      }
    }

  }

}

private fun getMenu(): MenuItem<String> {
  return dropDownMenu {
    item("home", "Home") {
      icon(Icons.Default.Home)
      item("profile", "Profile") {
        icon(Icons.Default.Person)
        item("edit_profile", "Edit Profile") {
          icon(Icons.Default.Edit)
          item("update_info", "Update Info") {
            icon(Icons.Default.Info)
          }
          item("change_password", "Change Password") {
            icon(Icons.Default.Lock)
          }
        }
      }
    }
    item("settings", "Settings") {
      icon(Icons.Default.Settings)
      item("account_settings", "Account Settings") {
        icon(Icons.Default.AccountCircle)
        item("notifications", "Notifications") {
          icon(Icons.Default.Notifications)
        }
      }
      item("app_settings", "App Settings") {
        icon(Icons.Default.Settings)
      }
    }
    item("help", "Help") {
      icon(Icons.Default.Info)

      item("email", "Email") {
        icon(Icons.Default.Email)
      }
      item("phone", "Phone") {
        icon(Icons.Default.Phone)
      }
    }
    item("logout", "Logout") {
      icon(Icons.AutoMirrored.Filled.ExitToApp)
    }
  }
}

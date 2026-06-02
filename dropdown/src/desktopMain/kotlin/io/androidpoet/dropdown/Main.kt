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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

public fun main(): Unit = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "Dropdown Demo",
    state = rememberWindowState(width = 600.dp, height = 400.dp),
  ) {
    MaterialTheme {
      Surface(modifier = Modifier.fillMaxSize()) {
        DropdownDemo()
      }
    }
  }
}

@Composable
private fun DropdownDemo() {
  var isOpen by remember { mutableStateOf(false) }
  var selectedOption by remember { mutableStateOf<String?>(null) }

  val menu = remember {
    dropDownMenu<String> {
      item("new", "New File") {
        icon(Icons.Default.Add)
      }
      item("open", "Open File") {
        icon(Icons.Default.FolderOpen)
      }
      horizontalDivider()
      item("save", "Save") {
        icon(Icons.Default.Save)
      }
      item("saveAs", "Save As...") {
        icon(Icons.Default.SaveAlt)
      }
      horizontalDivider()
      item("exit", "Exit") {
        icon(Icons.Default.Close)
      }
    }
  }

  Column(
    modifier = Modifier.fillMaxSize().padding(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("Dropdown Demo", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(24.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
      Text("Selected: ${selectedOption ?: "none"}")
      Spacer(Modifier.width(16.dp))
      Box {
        Button(onClick = { isOpen = !isOpen }) {
          Text("Menu")
        }
        Dropdown(
          isOpen = isOpen,
          menu = menu,
          onItemSelected = {
            selectedOption = it?.toString()
            isOpen = false
          },
          onDismiss = { isOpen = false },
          placement = MenuPlacement.Down,
        )
      }
    }
  }
}

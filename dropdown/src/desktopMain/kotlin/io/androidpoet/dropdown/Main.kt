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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
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
    title = "Cascade Dropdown",
    state = rememberWindowState(width = 460.dp, height = 300.dp),
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
  var selected by remember { mutableStateOf<String?>(null) }

  val menu = remember {
    dropDownMenu<String> {
      item("new", "New File") {
        icon(Icons.Default.FolderOpen)
      }
      item("open", "Open Recent") {
        icon(Icons.Default.Folder)
        item("doc1", "Resume.pdf")
        item("doc2", "Photo.png")
        item("doc3", "Notes.txt")
      }
      horizontalDivider()
      item("save", "Save") {
        icon(Icons.Default.Save)
      }
      horizontalDivider()
      item("cut", "Cut") {
        icon(Icons.Default.ContentCut)
      }
      item("copy", "Copy") {
        icon(Icons.Default.ContentCopy)
      }
      item("paste", "Paste") {
        icon(Icons.Default.ContentPaste)
      }
      horizontalDivider()
      item("prefs", "Preferences") {
        icon(Icons.Default.Settings)
        item("general", "General")
        item("editor", "Editor") {
          item("font", "Font")
          item("theme", "Theme")
        }
        item("plugins", "Plugins")
      }
    }
  }

  Column(
    modifier = Modifier.fillMaxSize().padding(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("Cascade Dropdown", style = MaterialTheme.typography.headlineMedium)
    Text(
      text = if (selected != null) "Selected: $selected" else "",
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(vertical = 16.dp),
    )
    Box {
      Button(onClick = { isOpen = !isOpen }) {
        Text("Open Menu")
      }
      Dropdown(
        isOpen = isOpen,
        menu = menu,
        onItemSelected = {
          selected = it?.toString()
          isOpen = false
        },
        onDismiss = { isOpen = false },
      )
    }
  }
}

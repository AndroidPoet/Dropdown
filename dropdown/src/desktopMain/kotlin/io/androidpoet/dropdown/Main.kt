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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

public fun main(): Unit = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "Dropdown — Nested Cascade Animation Demo",
    state = rememberWindowState(width = 820.dp, height = 620.dp),
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

  // ── Menu with 3-level nesting ─────────────────────────────────
  val menu = remember {
    dropDownMenu<String> {
      section("File")
      item("new", "New") {
        icon(Icons.Default.Add)
      }
      item("open", "Open...") {
        icon(Icons.Default.FolderOpen)
      }
      item("recent", "Recent Files") {
        icon(Icons.Default.FileCopy)
        item("doc1", "Resume.pdf")
        item("doc2", "Photo.png")
        item("doc3", "Notes.txt")
      }
      horizontalDivider()

      section("Edit")
      item("undo", "Undo") {
        icon(Icons.Default.Undo)
      }
      item("redo", "Redo") {
        icon(Icons.Default.Redo)
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

      section("Format")
      item("bold", "Bold") {
        icon(Icons.Default.FormatBold)
      }
      item("italic", "Italic") {
        icon(Icons.Default.FormatItalic)
      }
      item("underline", "Underline") {
        icon(Icons.Default.FormatUnderlined)
      }
      horizontalDivider()

      section("View")
      item("zoom", "Zoom") {
        icon(Icons.Default.ZoomIn)
        item("zoomIn", "Zoom In") {
          icon(Icons.Default.ZoomIn)
        }
        item("zoomOut", "Zoom Out") {
          icon(Icons.Default.ZoomOut)
        }
        item("zoomReset", "Reset to 100%")
      }
      item("fullscreen", "Toggle Fullscreen") {
        icon(Icons.Default.Close)
      }
      horizontalDivider()

      section("Help")
      item("about", "About") {
        icon(Icons.Default.Info)
      }
      item("settings", "Settings") {
        icon(Icons.Default.Settings)
        item("general", "General") {
          icon(Icons.Default.Settings)
        }
        item("editor", "Editor") {
          icon(Icons.Default.Edit)
        }
        item("plugins", "Plugins") {
          icon(Icons.Default.Build)
          item("plugin1", "Kotlin Support") {
            icon(Icons.Default.Code)
          }
          item("plugin2", "Markdown Preview") {
            icon(Icons.Default.Edit)
          }
        }
      }
    }
  }

  Column(
    modifier = Modifier.fillMaxSize().padding(24.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    Text(
      "Nested Cascade Animation Demo",
      style = MaterialTheme.typography.headlineSmall,
      fontWeight = FontWeight.Bold,
    )
    Text(
      "Navigate through nested menus → each level animates with custom transitions",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )

    Spacer(Modifier.height(8.dp))

    Card(
      modifier = Modifier.fillMaxWidth(),
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
      ),
    ) {
      Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        Text("Animation Legend", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Spacer(Modifier.height(6.dp))
        LegendRow(color = Color(0xFF4CAF50), label = "Forward (child): SlideInHorizontally + fade in")
        Spacer(Modifier.height(4.dp))
        LegendRow(color = Color(0xFFFF5722), label = "Backward (parent): SlideOutHorizontally + fade out")
        Spacer(Modifier.height(4.dp))
        LegendRow(color = Color(0xFF9C27B0), label = "Same-level: ScaleIn / fade")
        Spacer(Modifier.height(12.dp))
        Text(
          "Click arrow-right ▶ on items with children to drill down. " +
            "Click arrow-left ◀ on the header to go back.",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
    }

    Spacer(Modifier.height(8.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Box {
        Button(onClick = { isOpen = !isOpen }) {
          Text("Open Nested Menu")
        }
        Dropdown(
          isOpen = isOpen,
          menu = menu,
          offset = androidx.compose.ui.unit.DpOffset.Zero,
          onItemSelected = {
            selectedOption = it?.toString()
            isOpen = false
          },
          onDismiss = { isOpen = false },
          placement = MenuPlacement.Down,
          width = 220.dp,

          // ── Cascade animations ──────────────────────────
          childEnterAnimation = EnterAnimation.SlideInHorizontally,
          parentExitAnimation = ExitAnimation.SlideOutHorizontally,
          enter = EnterAnimation.FadeIn,
          exit = ExitAnimation.FadeOut,

          // ── Search ──────────────────────────────────────
          searchable = true,
          searchPlaceholder = "Filter items…",
        )
      }

      Spacer(Modifier.width(24.dp))

      OutlinedButton(onClick = { isOpen = !isOpen }) {
        Text("Toggle Menu")
      }
    }

    Spacer(Modifier.height(16.dp))

    Card(
      modifier = Modifier.fillMaxWidth(),
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
      ),
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text("Last selected:", fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(4.dp))
        Text(
          selectedOption ?: "— nothing selected yet —",
          fontFamily = FontFamily.Monospace,
          fontSize = 14.sp,
        )
      }
    }
  }
}

@Composable
private fun LegendRow(color: Color, label: String) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Box(
      modifier = Modifier
        .width(12.dp)
        .height(12.dp)
        .background(color, shape = androidx.compose.foundation.shape.CircleShape),
    )
    Spacer(Modifier.width(8.dp))
    Text(label, fontSize = 12.sp)
  }
}

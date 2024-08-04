import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.androidpoet.dropdownsample.App
import java.awt.Dimension

fun main() =
  application {
    Window(
      title = "DropdownSample",
      state = rememberWindowState(width = 800.dp, height = 600.dp),
      onCloseRequest = ::exitApplication,
    ) {
      window.minimumSize = Dimension(350, 600)
      App()
    }
  }

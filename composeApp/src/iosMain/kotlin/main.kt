import androidx.compose.ui.window.ComposeUIViewController
import io.androidpoet.dropdownsample.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }

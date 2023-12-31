import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, ExperimentalAnimationApi::class)
@Composable
fun App() {
    MaterialTheme {
        var isOpen by remember { mutableStateOf(false) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.TopEnd) {
                IconButton(onClick = { isOpen = true }) {
                    Icon(
                        imageVector = Icons.TwoTone.MoreVert,
                        contentDescription = null,
                    )
                }

                val menu = getMenu()
                Dropdown(
                    isOpen = isOpen,
                    menu = menu,
                    colors = dropDownMenuColors(Color(0xFF01D0BC), Color.Black),
                    onItemSelected = {
                    },
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

fun getMenu(): MenuItem<String> {
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
            icon(Icons.Default.ExitToApp)
        }
    }
}

expect fun getPlatformName(): String

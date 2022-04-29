
package com.androidpoet.cascademenucompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.DeleteSweep
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material.icons.twotone.FileCopy
import androidx.compose.material.icons.twotone.Language
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.androidpoet.cascademenucompose.ui.theme.CascadeMenuComposeTheme
import com.androidpoet.cascademenucompose.ui.theme.Teal200
import com.androidpoet.dropdown.Dropdown
import com.androidpoet.dropdown.Easing
import com.androidpoet.dropdown.EnterAnimation
import com.androidpoet.dropdown.ExitAnimation
import com.androidpoet.dropdown.MenuItem
import com.androidpoet.dropdown.dropDownMenu
import com.androidpoet.dropdown.dropDownMenuColors
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalAnimationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CascadeMenuComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          DropDownMenuScreen()
        }
      }
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  CascadeMenuComposeTheme {
    DropDownMenuScreen()
  }
}

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalAnimationApi
@Composable
fun DropDownMenuScreen() {
  val snackbarHostState = remember { SnackbarHostState() }
  val channel = remember { Channel<String>(Channel.CONFLATED) }
  val (isOpen, setIsOpen) = remember { mutableStateOf(false) }

  LaunchedEffect(key1 = channel) {
    channel.receiveAsFlow().collect {
      snackbarHostState.showSnackbar(it)
    }
  }
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    backgroundColor = Color.Transparent,
    scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.height(48.dp))
      Box(contentAlignment = Alignment.TopEnd) {
        Menu(isOpen = isOpen, setIsOpen = setIsOpen, itemSelected = {
          channel.trySend(it)
          setIsOpen(false)
        })
        IconButton(
          onClick = { setIsOpen(true) }
        ) {
          Icon(
            imageVector = Icons.TwoTone.MoreVert,
            contentDescription = null
          )
        }
      }
    }
  }
}

// Dropdown menu
@ExperimentalAnimationApi
@Composable
fun Menu(isOpen: Boolean = false, setIsOpen: (Boolean) -> Unit, itemSelected: (String) -> Unit) {
  val menu = getMenu()
  Dropdown(

    isOpen = isOpen,
    menu = menu,
    colors = dropDownMenuColors(Teal200, Color.Black),
    onItemSelected = itemSelected,
    onDismiss = { setIsOpen(false) },
    offset = DpOffset(8.dp, 0.dp),
    enter = EnterAnimation.ExpandVertically,
    exit = ExitAnimation.ShrinkVertically,
    easing = Easing.FastOutLinearInEasing,
    enterDuration = 400,
    exitDuration = 400

  )
}

// menu builder
fun getMenu(): MenuItem<String> {
  val menu = dropDownMenu<String> {
    item("about", "About") {
      icon(Icons.TwoTone.Language)
    }
    item("copy", "Copy") {
      icon(Icons.TwoTone.FileCopy)
    }
    item("share", "Share") {
      icon(Icons.TwoTone.Share)
      item("to_clipboard", "To clipboard") {
        item("pdf", "PDF")
        item("epub", "EPUB")
        item("web_page", "Web page")
        item("microsoft_word", "Microsoft word")
      }
      item("as_a_file", "As a file") {
        item("pdf", "PDF")
        item("epub", "EPUB")
        item("web_page", "Web page")
        item("microsoft_word", "Microsoft word")
      }
    }
    item("remove", "Remove") {
      icon(Icons.TwoTone.DeleteSweep)
      item("yep", "Yep") {
        icon(Icons.TwoTone.Done)
      }
      item("go_back", "Go back") {
        icon(Icons.TwoTone.Close)
      }
    }
  }
  return menu
}

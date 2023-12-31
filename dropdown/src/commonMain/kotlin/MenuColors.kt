import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// [DropDownMenuColors]  contains color information to be used for background color and content color.
public data class DropDownMenuColors(
    val backgroundColor: Color,
    val contentColor: Color,
)

// [dropDownMenuColors]  composable function to set background color and content color.
@Composable
public fun dropDownMenuColors(
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onSurface,
): DropDownMenuColors {
    return DropDownMenuColors(backgroundColor, contentColor)
}

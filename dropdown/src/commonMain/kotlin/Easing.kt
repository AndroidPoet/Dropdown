import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing

/**
 * Represents different easing options for animations.
 */
enum class Easing {
    FastOutSlowInEasing,
    LinearEasing,
    FastOutLinearInEasing,
    LinearOutSlowInEasing,
}

/**
 * Returns an easing instance based on the enum selection.
 *
 * @param enterAnimation The selected easing type.
 * @return The corresponding easing instance.
 */
@PublishedApi
internal fun getEasing(enterAnimation: Easing): androidx.compose.animation.core.Easing {
    return when (enterAnimation) {
        Easing.FastOutSlowInEasing -> FastOutSlowInEasing
        Easing.LinearEasing -> LinearEasing
        Easing.FastOutLinearInEasing -> FastOutLinearInEasing
        Easing.LinearOutSlowInEasing -> LinearOutSlowInEasing
    }
}

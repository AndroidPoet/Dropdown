import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset

/** MetaphorExitAnimation is the collection of the exit animations. */
public enum class ExitAnimation(public val value: Int) {
    FadeOut(1),
    SharedAxisXBackward(2),
    SharedAxisYBackward(3),
    SharedAxisZBackward(4),
    ElevationScale(5),
    SlideOut(6),
    SlideOutHorizontally(7),
    SlideOutVertically(8),
    ScaleOut(9),
    ShrinkOut(10),
    ShrinkHorizontally(11),
    ShrinkVertically(12),
}

/**
 * Provides the ExitTransition based on the specified [animationProp] and [exitAnimation].
 * This function generates various exit animations for different scenarios.
 *
 * @param animationProp The properties for the animation.
 * @param exitAnimation The selected ExitAnimation type.
 * @return The ExitTransition for the specified animation.
 */

@PublishedApi
internal fun getExitAnimation(
    animationProp: AnimationProp,
    exitAnimation: ExitAnimation,
): ExitTransition {
    val commonAnimation =
        when (exitAnimation) {
            ExitAnimation.FadeOut,
            ExitAnimation.SharedAxisXBackward,
            ExitAnimation.SharedAxisYBackward,
            ExitAnimation.SharedAxisZBackward,
            ExitAnimation.ElevationScale,
            -> {
                fadeOut(animationSpec = getAnimationSpec(animationProp))
            }

            ExitAnimation.SlideOut -> {
                slideOut(
                    targetOffset = {
                        IntOffset(-it.width / 2, it.height / 2)
                    },
                    animationSpec = getOffsetAnimationSpec(animationProp),
                )
            }

            ExitAnimation.SlideOutHorizontally -> {
                slideOutHorizontally(animationSpec = getOffsetAnimationSpec(animationProp))
            }

            ExitAnimation.SlideOutVertically -> {
                slideOutVertically(animationSpec = getOffsetAnimationSpec(animationProp))
            }

            ExitAnimation.ScaleOut,
            ExitAnimation.ShrinkOut,
            ExitAnimation.ShrinkHorizontally,
            ExitAnimation.ShrinkVertically,
            -> {
                scaleOut(animationSpec = getAnimationSpec(animationProp))
            }
        }

    return when (exitAnimation) {
        ExitAnimation.SharedAxisZBackward, ExitAnimation.ElevationScale -> {
            commonAnimation + scaleOut(animationSpec = getAnimationSpec(animationProp))
        }

        else -> commonAnimation
    }
}

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.unit.IntOffset

/** MetaphorEnterAnimation is the collection of the  enter animations. */
enum class EnterAnimation(val value: Int) {
    FadeIn(1),
    SharedAxisXForward(2),
    SharedAxisYForward(3),
    SharedAxisZForward(4),
    ElevationScale(5),
    SlideIn(
        6,
    ),
    SlideInHorizontally(7),
    SlideInVertically(8),
    ScaleIn(9),
    ExpandIn(10),
    ExpandHorizontally(11),
    ExpandVertically(
        12,
    ),
}

/**
 * Returns the appropriate EnterTransition based on the animation properties and selected EnterAnimation.
 *
 * @param animationProp The properties for the animation.
 * @param enterAnimation The selected EnterAnimation type.
 * @return The EnterTransition for the specified animation.
 */

@PublishedApi
internal fun getEnterAnimation(
    animationProp: AnimationProp,
    enterAnimation: EnterAnimation,
): EnterTransition {
    val commonAnimation =
        when (enterAnimation) {
            EnterAnimation.FadeIn, EnterAnimation.SharedAxisXForward, EnterAnimation.SharedAxisYForward, EnterAnimation.SharedAxisZForward, EnterAnimation.ElevationScale -> {
                fadeIn(animationSpec = getAnimationSpec(animationProp))
            }

            EnterAnimation.SlideIn -> {
                slideIn(
                    animationSpec = getOffsetAnimationSpec(animationProp),
                    initialOffset = { IntOffset(it.width / 2, it.height / 2) },
                )
            }

            EnterAnimation.SlideInHorizontally -> {
                slideInHorizontally(animationSpec = getOffsetAnimationSpec(animationProp))
            }

            EnterAnimation.SlideInVertically -> {
                slideInVertically(animationSpec = getOffsetAnimationSpec(animationProp))
            }

            EnterAnimation.ScaleIn, EnterAnimation.ExpandIn, EnterAnimation.ExpandHorizontally, EnterAnimation.ExpandVertically -> {
                scaleIn(animationSpec = getAnimationSpec(animationProp))
            }
        }

    return when (enterAnimation) {
        EnterAnimation.SharedAxisZForward, EnterAnimation.ElevationScale -> {
            commonAnimation + scaleIn(animationSpec = getAnimationSpec(animationProp))
        }

        else -> commonAnimation
    }
}

fun getAnimationSpec(animationProp: AnimationProp): TweenSpec<Float> {
    return tween(
        durationMillis = animationProp.enterDuration,
        delayMillis = animationProp.delay,
        easing = getEasing(animationProp.easing),
    )
}

fun getOffsetAnimationSpec(animationProp: AnimationProp): FiniteAnimationSpec<IntOffset> {
    return tween(
        durationMillis = animationProp.enterDuration,
        delayMillis = animationProp.delay,
        easing = getEasing(animationProp.easing),
    )
}

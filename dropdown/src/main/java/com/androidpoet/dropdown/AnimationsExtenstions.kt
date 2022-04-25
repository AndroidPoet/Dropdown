
package com.androidpoet.dropdown

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset

/*[EnterAnimation] will return enter animation instance ont he basis of enum selection */
@OptIn(ExperimentalAnimationApi::class)
@JvmSynthetic
@PublishedApi
internal fun getMetaphorEnterAnimation(
  animationProp: AnimationProp,
  enterAnimation: EnterAnimation
): EnterTransition {
  when (enterAnimation) {
    EnterAnimation.FadeIn -> {
      return fadeIn(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        ),

      )
    }
    EnterAnimation.SharedAxisXForward -> {

      return slideInHorizontally(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + fadeIn(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        ),

      )
    }

    EnterAnimation.SharedAxisYForward -> {

      return slideInVertically(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + fadeIn(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    EnterAnimation.SharedAxisZForward -> {

      return fadeIn(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + scaleIn(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    EnterAnimation.ElevationScale -> {
      return fadeIn(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + scaleIn(
        animationSpec = tween(
          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    EnterAnimation.SlideIn -> {
      return slideIn(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        ),
        initialOffset = { fullSize ->
          IntOffset(
            fullSize.width / 2,
            fullSize.height / 2
          )
        }

      )
    }

    EnterAnimation.SlideInHorizontally -> {
      return slideInVertically(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        )

      )
    }

    EnterAnimation.SlideInVertically -> {
      return slideInVertically(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        )

      )
    }

    EnterAnimation.ScaleIn -> {
      return scaleIn(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        )

      )
    }

    EnterAnimation.ExpandIn -> {
      return expandIn(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        )

      )
    }

    EnterAnimation.ExpandHorizontally -> {
      return expandHorizontally(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        )

      )
    }

    EnterAnimation.ExpandVertically -> {
      return expandVertically(
        animationSpec = tween(

          durationMillis = animationProp.enterDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)

        )

      )
    }
  }
}

/*[ExitAnimation] will return exit animation instance ont he basis of enum selection */
@OptIn(ExperimentalAnimationApi::class)
@JvmSynthetic
@PublishedApi
internal fun getMetaphorExitAnimation(
  animationProp: AnimationProp,
  enterAnimation: ExitAnimation
): ExitTransition {

  when (enterAnimation) {

    ExitAnimation.FadeOut -> {
      return fadeOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }
    ExitAnimation.SharedAxisXBackward -> {

      return slideOutHorizontally(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + fadeOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.SharedAxisYBackward -> {
      return slideOutVertically(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + fadeOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.SharedAxisZBackward -> {

      return fadeOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + scaleOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.ElevationScale -> {
      return fadeOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      ) + scaleOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.SlideOut -> {
      return slideOut(

        targetOffset = { fullSize ->
          IntOffset(
            -fullSize.width / 2,
            fullSize.height / 2
          )
        },

        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.SlideOutHorizontally -> {
      return slideOutHorizontally(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }
    ExitAnimation.SlideOutVertically -> {
      return slideOutVertically(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.ScaleOut -> {
      return scaleOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.ShrinkOut -> {
      return shrinkOut(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.ShrinkHorizontally -> {
      return shrinkHorizontally(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }

    ExitAnimation.ShrinkVertically -> {
      return shrinkVertically(
        animationSpec = tween(
          durationMillis = animationProp.exitDuration,
          delayMillis = animationProp.delay,
          easing = getMetaphorEasing(animationProp.easing)
        )
      )
    }
  }
}

/*[Easing] will return easing instance to perform animation  on the  basis of enum selection */
@OptIn(ExperimentalAnimationApi::class)
@JvmSynthetic
@PublishedApi
internal fun getMetaphorEasing(enterAnimation: com.androidpoet.dropdown.Easing): Easing {

  when (enterAnimation) {

    com.androidpoet.dropdown.Easing.FastOutSlowInEasing -> {

      return FastOutSlowInEasing
    }

    com.androidpoet.dropdown.Easing.LinearEasing -> {
      return LinearEasing
    }

    com.androidpoet.dropdown.Easing.FastOutLinearInEasing -> {

      return FastOutLinearInEasing
    }

    com.androidpoet.dropdown.Easing.LinearOutSlowInEasing -> {
      return LinearOutSlowInEasing
    }
  }
}

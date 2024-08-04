/*
 * Designed and developed by 2022 androidpoet (Ranbir Singh)
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

/** EnterAnimation is the collection of the  enter animations. */
public enum class EnterAnimation(public val value: Int) {
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
  val spec = getAnimationSpec(animationProp)
  val offsetSpec = getOffsetAnimationSpec(animationProp)

  val baseAnimation =
    when (enterAnimation) {
      EnterAnimation.FadeIn, EnterAnimation.SharedAxisXForward,
      EnterAnimation.SharedAxisYForward, EnterAnimation.SharedAxisZForward,
      EnterAnimation.ElevationScale,
      -> fadeIn(spec)

      EnterAnimation.SlideIn -> slideIn(offsetSpec) { IntOffset(it.width / 2, it.height / 2) }
      EnterAnimation.SlideInHorizontally -> slideInHorizontally(offsetSpec)
      EnterAnimation.SlideInVertically -> slideInVertically(offsetSpec)
      EnterAnimation.ScaleIn, EnterAnimation.ExpandIn,
      EnterAnimation.ExpandHorizontally, EnterAnimation.ExpandVertically,
      -> scaleIn(spec)
    }

  return if (enterAnimation in
    listOf(
      EnterAnimation.SharedAxisZForward,
      EnterAnimation.ElevationScale,
    )
  ) {
    baseAnimation + scaleIn(spec)
  } else {
    baseAnimation
  }
}

public fun getAnimationSpec(animationProp: AnimationProp): TweenSpec<Float> {
  return tween(
    durationMillis = animationProp.enterDuration,
    delayMillis = animationProp.delay,
    easing = getEasing(animationProp.easing),
  )
}

public fun getOffsetAnimationSpec(animationProp: AnimationProp): FiniteAnimationSpec<IntOffset> {
  return tween(
    durationMillis = animationProp.enterDuration,
    delayMillis = animationProp.delay,
    easing = getEasing(animationProp.easing),
  )
}

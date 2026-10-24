/*
 * Designed and developed by 2024 androidpoet (Ranbir Singh)
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

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

public data class AnimationProp(
  val enterDuration: Int,
  val exitDuration: Int,
  val delay: Int = 0,
  val easing: Easing,
  val initialAlpha: Float = 0.92f,
)

internal fun getSizeAnimationSpec(animationProp: AnimationProp): TweenSpec<IntSize> =
  tween(
    durationMillis = animationProp.enterDuration,
    delayMillis = animationProp.delay,
    easing = getEasing(animationProp.easing),
  )

internal fun getExitAnimationSpec(animationProp: AnimationProp): TweenSpec<Float> =
  tween(
    durationMillis = animationProp.exitDuration,
    delayMillis = animationProp.delay,
    easing = getEasing(animationProp.easing),
  )

internal fun getExitOffsetAnimationSpec(animationProp: AnimationProp): FiniteAnimationSpec<IntOffset> =
  tween(
    durationMillis = animationProp.exitDuration,
    delayMillis = animationProp.delay,
    easing = getEasing(animationProp.easing),
  )

internal fun getExitSizeAnimationSpec(animationProp: AnimationProp): TweenSpec<IntSize> =
  tween(
    durationMillis = animationProp.exitDuration,
    delayMillis = animationProp.delay,
    easing = getEasing(animationProp.easing),
  )

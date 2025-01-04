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

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing

/**
 * Represents different easing options for animations.
 */
public enum class Easing {
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

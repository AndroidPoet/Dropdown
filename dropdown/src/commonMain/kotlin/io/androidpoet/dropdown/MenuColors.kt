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

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// [DropDownMenuColors]  contains color information to be used for background color and content color.
public data class DropDownMenuColors(
  val backgroundColor: Color,
  val contentColor: Color,
) {
  public companion object {
    /** Uses the theme's surface color (default). */
    @Composable
    public fun default(): DropDownMenuColors = dropDownMenuColors()

    /** Dark theme preset with elevated surface and inverted content. */
    @Composable
    public fun dark(): DropDownMenuColors = DropDownMenuColors(
      backgroundColor = Color(0xFF2D2D2D),
      contentColor = Color(0xFFE0E0E0),
    )

    /** Minimal light preset with very subtle styling. */
    @Composable
    public fun minimal(): DropDownMenuColors = DropDownMenuColors(
      backgroundColor = Color(0xFFF8F8F8),
      contentColor = Color(0xFF333333),
    )

    /** Uses the theme's primary color as background with matching content. */
    @Composable
    public fun primary(): DropDownMenuColors = DropDownMenuColors(
      backgroundColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.onPrimary,
    )
  }
}

public object ContentAlpha {
  public const val HIGH: Float = 1.0f
  public const val MEDIUM: Float = 0.6f
  public const val DISABLED: Float = 0.38f
}

// [dropDownMenuColors]  composable function to set background color and content color.
@Composable
public fun dropDownMenuColors(
  backgroundColor: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = MaterialTheme.colorScheme.onSurface,
): DropDownMenuColors {
  return DropDownMenuColors(backgroundColor, contentColor)
}

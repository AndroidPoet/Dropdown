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
package io.androidpoet.dropdown.baselineprofile


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test

@RequiresApi(Build.VERSION_CODES.P)
class BaselineProfileGenerator {
  @get:Rule
  val baselineProfileRule = BaselineProfileRule()

  private val targetPackage = "io.androidpoet.dropdown.baselineprofile.app"

  @Test
  fun generateBaselineProfile() = baselineProfileRule.collect(
    packageName = targetPackage
  ) {
    pressHome()
    startActivityAndWait()
    device.waitForIdle()

    // Open dropdown using the more button
    device.findObject(By.desc("More options"))?.let { moreButton ->
      moreButton.click()
      device.waitForIdle()

      // Test Home menu tree
      device.findObject(By.text("Home"))?.let { homeMenu ->
        homeMenu.click()
        device.waitForIdle()

        // Profile submenu
        device.findObject(By.text("Profile"))?.let { profile ->
          profile.click()
          device.waitForIdle()

          // Edit Profile submenu
          device.findObject(By.text("Edit Profile"))?.let { editProfile ->
            editProfile.click()
            device.waitForIdle()

            // Update Info
            device.findObject(By.text("Update Info"))?.click()
            device.waitForIdle()

            // Change Password
            device.findObject(By.text("Change Password"))?.click()
            device.waitForIdle()
          }
        }
      }

      // Close and reopen dropdown
      moreButton.click()
      device.waitForIdle()

      // Test Settings menu tree
      device.findObject(By.text("Settings"))?.let { settingsMenu ->
        settingsMenu.click()
        device.waitForIdle()

        // Account Settings
        device.findObject(By.text("Account Settings"))?.let { accountSettings ->
          accountSettings.click()
          device.waitForIdle()

          // Notifications
          device.findObject(By.text("Notifications"))?.click()
          device.waitForIdle()
        }

        // App Settings
        device.findObject(By.text("App Settings"))?.click()
        device.waitForIdle()
      }

      // Close and reopen dropdown
      moreButton.click()
      device.waitForIdle()

      // Test Help menu tree
      device.findObject(By.text("Help"))?.let { helpMenu ->
        helpMenu.click()
        device.waitForIdle()

        // Email
        device.findObject(By.text("Email"))?.click()
        device.waitForIdle()

        // Phone
        device.findObject(By.text("Phone"))?.click()
        device.waitForIdle()
      }

      // Close and reopen dropdown
      moreButton.click()
      device.waitForIdle()

      // Test Logout
      device.findObject(By.text("Logout"))?.click()
      device.waitForIdle()
    }

    // Final test of opening/closing
    device.findObject(By.desc("More options"))?.let { moreButton ->
      // Open dropdown
      moreButton.click()
      device.waitForIdle()

      // Close dropdown by clicking outside
      device.click(0, 0)
      device.waitForIdle()
    }
  }
}
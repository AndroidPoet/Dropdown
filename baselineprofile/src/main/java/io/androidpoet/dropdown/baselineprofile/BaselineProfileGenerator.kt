package io.androidpoet.dropdown.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

class BaselineProfileGenerator {
  @get:Rule
  val baselineProfileRule = BaselineProfileRule()

  @Test
  fun startup() = baselineProfileRule.collect(
    packageName = "io.androidpoet.dropdown.androidApp",
    stableIterations = 2,
    maxIterations = 8,
  ) {
    pressHome()
    // This block defines the app's critical user journey. Here we are interested in
    // optimizing for app startup. But you can also navigate and scroll
    // through your most important UI.
    startActivityAndWait()
    device.waitForIdle()
  }
}
import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
  alias(libs.plugins.android.test)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.baseline.profile)
}

android {
  namespace = "io.androidpoet.dropdown.baselineprofile"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  defaultConfig {
    minSdk = 28
    targetSdk = 34

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  targetProjectPath = ":composeApp"

  testOptions.managedDevices.devices {
    maybeCreate<ManagedVirtualDevice>("pixel6api31").apply {
      device = "Pixel 6"
      apiLevel = 31
      systemImageSource = "aosp"
    }
  }
}

// This is the plugin configuration. Everything is optional. Defaults are in the
// comments. In this example, you use the GMD added earlier and disable connected devices.
baselineProfile {

  // This specifies the managed devices to use that you run the tests on. The default
  // is none.
  managedDevices += "pixel6api31"

  // This enables using connected devices to generate profiles. The default is true.
  // When using connected devices, they must be rooted or API 33 and higher.
  useConnectedDevices = false
}

dependencies {
  implementation(libs.androidx.junit)
  implementation(libs.androidx.espresso.core)
  implementation(libs.androidx.uiautomator)
  implementation(libs.androidx.benchmark.macro.junit4)
}


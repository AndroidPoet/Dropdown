import io.androidpoet.dropdown.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(
    libs.plugins.android.application
      .get()
      .pluginId,
  )
  id(
    libs.plugins.kotlin.android
      .get()
      .pluginId,
  )
  id(
    libs.plugins.compose.compiler
      .get()
      .pluginId,
  )
  id(
    libs.plugins.baseline.profile
      .get()
      .pluginId,
  )
}

android {
  namespace = "io.androidpoet.dropdown.baselineprofile.app"
  compileSdk = Configuration.compileSdk

  defaultConfig {
    applicationId = "io.androidpoet.dropdown.baselineprofile.app"
    minSdk = 23
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = libs.versions.jvmTarget.get()
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  lint {
    abortOnError = false
  }

  buildTypes {
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }
}

dependencies {

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.material3)
  implementation(project(":dropdown"))
  baselineProfile(project(":baselineprofile"))
}

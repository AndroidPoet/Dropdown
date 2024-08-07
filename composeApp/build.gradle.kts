import com.android.build.api.dsl.ManagedVirtualDevice
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
  alias(libs.plugins.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.compose)
  alias(libs.plugins.android.application)
  alias(libs.plugins.baseline.profile)
}

kotlin {
  androidTarget {
    compilations.all {
      compileTaskProvider {
        compilerOptions {
          jvmTarget.set(JvmTarget.JVM_11)
          freeCompilerArgs.add("-Xjdk-release=${JavaVersion.VERSION_11}")
        }
      }
    }
    // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    instrumentedTestVariant {
      sourceSetTree.set(KotlinSourceSetTree.test)
      dependencies {
        debugImplementation(libs.androidx.testManifest)
        implementation(libs.androidx.junit4)
      }
    }
  }

  jvm()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  ).forEach {
    it.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(project(":dropdown"))
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      @OptIn(ExperimentalComposeLibrary::class)
      implementation(compose.uiTest)
    }

    androidMain.dependencies {
      implementation(compose.uiTooling)
      implementation(libs.androidx.activityCompose)
    }

    jvmMain.dependencies {
      implementation(compose.desktop.currentOs)
    }

    iosMain.dependencies {}
  }
}

android {
  namespace = "io.androidpoet.dropdown"
  compileSdk = 34

  defaultConfig {
    minSdk = 21
    targetSdk = 34

    applicationId = "io.androidpoet.dropdown.androidApp"
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  sourceSets["main"].apply {
    manifest.srcFile("src/androidMain/AndroidManifest.xml")
    res.srcDirs("src/androidMain/res")
  }
  // https://developer.android.com/studio/test/gradle-managed-devices
  @Suppress("UnstableApiUsage")
  testOptions {
    managedDevices.devices {
      maybeCreate<ManagedVirtualDevice>("pixel5").apply {
        device = "Pixel 5"
        apiLevel = 34
        systemImageSource = "aosp"
      }
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }

  packaging {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }

  lint {
    abortOnError = false
  }

  kotlin {
    jvmToolchain(11)
  }
}

compose.desktop {
  application {
    mainClass = "MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "io.androidpoet.dropdown.desktopApp"
      packageVersion = "1.0.0"
    }
  }
}

task("testClasses") {}
//dependencies {
//  implementation(libs.androidx.profileinstaller)
//  "baselineProfile"(project(":baselineprofile"))
//}

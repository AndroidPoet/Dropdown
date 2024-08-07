plugins {
  alias(libs.plugins.multiplatform) apply (false)
  alias(libs.plugins.compose.compiler) apply (false)
  alias(libs.plugins.compose) apply (false)
  alias(libs.plugins.android.application) apply (false)
  alias(libs.plugins.android.library) apply (false)
  alias(libs.plugins.baseline.profile) apply false
  alias(libs.plugins.kotlin.binary.compatibility) apply false
  alias(libs.plugins.nexus.plugin) apply false
  alias(libs.plugins.spotless) apply false
  alias(libs.plugins.dokka) apply false
  alias(libs.plugins.android.test) apply false
  alias(libs.plugins.jetbrains.kotlin.android) apply false
}
subprojects {
  apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
      target("**/*.kt")
      targetExclude("$buildDir/**/*.kt")
      ktlint().editorConfigOverride(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2",
        ),
      )
      licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
      trimTrailingWhitespace()
      endWithNewline()
    }
    format("xml") {
      target("**/*.xml")
      targetExclude("**/build/**/*.xml")
      // Look for the first XML tag that isn't a comment (<!--) or the xml declaration (<?xml)
      licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
      trimTrailingWhitespace()
      endWithNewline()
    }
  }
}

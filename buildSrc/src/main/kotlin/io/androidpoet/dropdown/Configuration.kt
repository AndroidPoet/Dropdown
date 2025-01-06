package io.androidpoet.dropdown

object Configuration {
  const val compileSdk = 34
  const val targetSdk = 33
  const val minSdk = 21
  const val majorVersion = 1
  const val minorVersion = 1
  const val patchVersion = 6
  const val versionName = "$majorVersion.$minorVersion.$patchVersion"
  const val versionCode = 1
  const val snapshotVersionName = "$majorVersion.$minorVersion.${patchVersion + 1}-SNAPSHOT"
  const val artifactGroup = "io.github.androidpoet"
}

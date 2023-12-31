rootProject.name = "Dropdown"

include(":sample:androidApp")
include(":dropdown")
include(":sample:desktopApp")
include(":sample:shared")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String
        val dokkaVersion = extra["dokka.version"] as String
        val klintVersion = extra["klint.version"] as String
        val mavenPublishVersion = extra["maven-publish.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)

        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)

        id("org.jetbrains.compose").version(composeVersion)
        id("org.jetbrains.dokka").version(dokkaVersion)
        id("org.jlleitschuh.gradle.ktlint").version(klintVersion)
        id("com.vanniktech.maven.publish").version(mavenPublishVersion)
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

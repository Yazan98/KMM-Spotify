pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm").version("1.8.20")
        kotlin("multiplatform").version("1.8.20")
        kotlin("android").version("1.8.20")

        id("com.android.application").version("8.0.2")
        id("com.android.library").version("8.0.2")
        id("org.jetbrains.compose").version("1.4.0")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "Radio"
include(":androidApp")
include(":shared")
include(":sopify")
include(":androidFeatures:core")
include(":androidFeatures:auth")
include(":androidFeatures:home")
include(":compose")

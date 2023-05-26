pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Radio"
include(":androidApp")
include(":shared")
include(":sopify")
include(":androidFeatures:core")
include(":androidFeatures:auth")
include(":androidFeatures:home")

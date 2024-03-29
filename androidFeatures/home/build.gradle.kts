plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.yazantarifi.radio.android.home"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

}


dependencies {
    addJetpackComposeDependencies(this)
    addMaterialConfiguration(this)
    setupHiltConfigurations(this)

    implementation(project(":shared"))
//    implementation(project(":sopify"))
    implementation(project(":androidFeatures:core"))
//    implementation(project(mapOf("path" to ":compose")))

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    implementation("io.ktor:ktor-client-core:2.2.1")
    implementation("io.ktor:ktor-client-android:2.2.1")
}


fun addJetpackComposeDependencies(configuration: DependencyHandlerScope) {
    configuration.implementation("androidx.core:core-ktx:1.7.0")
    configuration.implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    configuration.implementation("androidx.activity:activity-compose:1.4.0")
    configuration.implementation("androidx.compose.ui:ui:1.2.0")
    configuration.implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    configuration.debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    configuration.implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    configuration.implementation("androidx.navigation:navigation-compose:2.4.2")
    configuration.implementation("com.google.accompanist:accompanist-pager:0.24.8-beta")
    configuration.implementation("com.google.accompanist:accompanist-pager-indicators:0.24.8-beta")
    configuration.implementation("io.coil-kt:coil-compose:2.0.0")
    configuration.implementation("androidx.core:core-animation:1.0.0-beta01")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha03")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.13-rc")
    configuration.implementation("com.google.android.material:material:1.8.0-alpha01")
    configuration.implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")
}

fun setupHiltConfigurations(configurations: DependencyHandlerScope) {
    configurations.implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    configurations.implementation("com.google.dagger:hilt-android:2.44")
    configurations.kapt("com.google.dagger:hilt-android-compiler:2.44")
}

fun addMaterialConfiguration(configuration: DependencyHandlerScope) {
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha11")
    configuration.implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    configuration.implementation("androidx.compose.animation:animation:1.1.1")
    configuration.implementation("androidx.compose.animation:animation-core:1.1.1")
}
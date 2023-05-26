plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlinx-serialization")
    id("io.realm.kotlin")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.ktor:ktor-client-core:2.2.1")
                implementation("io.ktor:ktor-client-logging:2.2.1")
                implementation("io.ktor:ktor-client-serialization:2.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.2.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.1")
                implementation("io.realm.kotlin:library-base:1.4.0")
                implementation(project(mapOf("path" to ":sopify")))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(project(mapOf("path" to ":sopify")))
                implementation("io.ktor:ktor-client-android:2.2.1")
                implementation("io.ktor:ktor-client-json:2.2.1")
                implementation("io.ktor:ktor-client-serialization-jvm:2.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.2.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.1")
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(project(mapOf("path" to ":sopify")))
                implementation("io.ktor:ktor-client-ios:2.2.1")
                implementation("io.ktor:ktor-client-darwin:2.2.1")
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.yazantarifi.radio"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

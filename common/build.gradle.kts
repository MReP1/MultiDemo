import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "little.goose"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                // Ktor-core
                implementation("io.ktor:ktor-client-core:${extra["ktor.version"]}")
                // Logging
                implementation("io.ktor:ktor-client-logging:${extra["ktor.version"]}")
                implementation("ch.qos.logback:logback-classic:1.2.11")
                // Negotiation
                implementation("io.ktor:ktor-client-content-negotiation:${extra["ktor.version"]}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${extra["ktor.version"]}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.0")
                api("androidx.core:core-ktx:1.8.0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
                implementation("io.ktor:ktor-client-okhttp:${extra["ktor.version"]}")

                // Coil
                implementation("io.coil-kt:coil:2.1.0")
                implementation("io.coil-kt:coil-compose:2.1.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
                implementation("io.ktor:ktor-client-cio:${extra["ktor.version"]}")
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.network")

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.ktor.engine.okhttp)
                implementation(libs.kotlinx.coroutines.android)
            }
        }
        commonMain {
            dependencies {
                api(projects.fashion)

                api(libs.ktor.core)

                implementation(libs.kotlinx.atomicfu)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.auth)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.serialization)
            }
        }
        appleMain {
            dependencies {
                implementation(libs.ktor.engine.darwin)
            }
        }
    }
}

import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.compose")

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.ktor.engine.okhttp)
            }
        }
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.material)
                implementation(libs.coil3.compose)
                implementation(libs.coil3.network.ktor3)
                implementation(libs.compose.placeholder)
            }
        }
        appleMain {
            dependencies {
                implementation(libs.ktor.engine.darwin)
            }
        }
    }
}

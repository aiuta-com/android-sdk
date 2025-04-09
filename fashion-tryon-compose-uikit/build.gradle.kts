import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.tryon.compose.uikit")

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.ktor.engine.okhttp)
            }
        }
        commonMain {
            dependencies {
                api(projects.fashionTryonComposeConfigurationUi)

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

import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.tryon.compose.defaults.icons")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionTryonComposeConfiguration)
                api(projects.fashionTryonComposeConfigurationUi)

                implementation(compose.foundation)
                implementation(compose.components.resources)
            }
        }
    }
}

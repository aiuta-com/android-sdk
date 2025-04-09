import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.tryon.compose.defaults")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionTryonComposeDefaultsIcons)
                api(projects.fashionTryonComposeDefaultsImages)

                api(compose.runtime)
            }
        }
    }
}

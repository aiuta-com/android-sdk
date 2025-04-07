import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.tryon.compose.configuration.ui")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionTryonComposeResources)

                implementation(compose.components.resources)
                implementation(compose.foundation)
                implementation(compose.ui)
            }
        }
    }
}

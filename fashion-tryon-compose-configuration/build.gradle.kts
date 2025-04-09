import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.tryon.compose.configuration")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionTryonCore)
                api(projects.fashionTryonComposeResources)

                implementation(compose.foundation)
                implementation(compose.ui)

                implementation(projects.internal.analytic)
            }
        }
    }
}

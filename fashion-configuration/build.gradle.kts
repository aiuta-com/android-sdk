import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.configuration")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashion)
                api(projects.fashionComposeResources)
                api(projects.internal.internalFashionAnalytics)

                implementation(compose.foundation)
            }
        }
    }
}

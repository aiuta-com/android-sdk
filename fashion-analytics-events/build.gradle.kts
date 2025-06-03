import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.analytics.events")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization)
            }
        }
    }
}

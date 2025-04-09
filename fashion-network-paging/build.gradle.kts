import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.network.paging")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.paging.common)
                implementation(libs.kotlinx.serialization)
                implementation(libs.ktor.core)
            }
        }
    }
}

import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.kotlinx.serialization)
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.tryon.core")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashion)
                api(projects.fashionNetwork)
                api(projects.fashionNetworkPaging)

                implementation(compose.ui)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)

                implementation(projects.internal.analytic)
            }
        }
    }
}

import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    alias(libs.plugins.kotlinx.serialization)
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.network.paging")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionNetwork)

                implementation(libs.androidx.paging.common)
                implementation(libs.kotlinx.serialization)
                implementation(libs.ktor.core)
            }
        }
    }
}

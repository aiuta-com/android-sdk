import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.tryon.paging")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionTryonCore)

                api(libs.androidx.paging.common)
            }
        }
    }
}

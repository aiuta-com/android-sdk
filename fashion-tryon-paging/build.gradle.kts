import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.tryon.paging")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionTryonCore)

                api(libs.androidx.paging)
            }
        }
    }
}

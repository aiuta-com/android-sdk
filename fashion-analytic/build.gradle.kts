import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.analytic")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.internal.analytic)

                api(libs.kotlinx.coroutines.core)
            }
        }
    }
}

import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.analytic")

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

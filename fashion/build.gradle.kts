import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashionLogger)
            }
        }
    }
}

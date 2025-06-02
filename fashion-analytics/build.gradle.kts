import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.analytics")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlinx.coroutines.core)
                api(projects.fashionAnalyticsEvents)

                implementation(projects.internal.internalFashionAnalytics)
            }
        }
    }
}

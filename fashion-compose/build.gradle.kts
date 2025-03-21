import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.compose")

//dependencies {
//    implementation(libs.androidx.compose.material)
//    implementation(libs.androidx.lifecycle.runtime.compose)
//    implementation(libs.coil.compose)
//}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.material)
//                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.coil3.compose)
            }
        }
    }
}

import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("androidx.baselineprofile")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-android")
}

androidLibrary(name = "com.aiuta.fashionsdk.compose")

baselineProfile {
    mergeIntoMain = true
    saveInSrc = true
    baselineProfileOutputDir = ""
    filter {
        include("com.aiuta.fashionsdk.**")
    }
}

dependencies {
    baselineProfile(projects.internal.benchmark)

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.lifecycle.runtime.compose)
}

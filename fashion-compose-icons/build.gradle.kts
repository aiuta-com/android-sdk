import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("androidx.baselineprofile")
    id("com.android.library")
    id("kotlin-android")
}

androidLibrary(
    name = "com.aiuta.fashionsdk.compose.icons",
    composeLibrary = true,
)

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

    implementation(projects.fashionCompose)
//    implementation(libs.androidx.compose.material)
//    implementation(libs.androidx.lifecycle.runtime.compose)
}

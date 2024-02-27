import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("androidx.baselineprofile")
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.kotlinx.serialization)
}

androidLibrary(name = "com.aiuta.fashionsdk.tryon.core")

baselineProfile {
    mergeIntoMain = true
    saveInSrc = true
    baselineProfileOutputDir = ""
    filter {
        include("com.aiuta.fashionsdk.tryon.**")
        exclude("com.aiuta.fashionsdk.network.**")
    }
}

dependencies {
    api(projects.fashion)
    api(projects.fashionNetwork)
    api(projects.fashionNetworkPaging)

    baselineProfile(projects.internal.benchmark)

    implementation(libs.androidx.exifinterface)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.core)
}

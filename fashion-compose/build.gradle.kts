import com.aiuta.fashionsdk.androidLibrary
import org.jetbrains.compose.ExperimentalComposeLibrary

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

    @OptIn(ExperimentalComposeLibrary::class)
    implementation(compose.components.resources)
    implementation(compose.material)

    implementation(libs.androidx.lifecycle.runtime.compose)
}

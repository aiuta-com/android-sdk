import com.aiuta.fashionsdk.androidLibrary

plugins {
    alias(libs.plugins.ksp)
    id("androidx.baselineprofile")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-android")
}

androidLibrary(name = "com.aiuta.fashionsdk.tryon.compose")

baselineProfile {
    mergeIntoMain = true
    saveInSrc = true
    baselineProfileOutputDir = ""
    filter {
        include("com.aiuta.fashionsdk.tryon.**")
    }
}

dependencies {
    api(projects.fashionCompose)
    api(projects.fashionTryonCore)

    annotationProcessor(libs.androidx.room.compiler)

    baselineProfile(projects.internal.benchmark)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.paging.compose)
    implementation(libs.coil.compose)
    implementation(libs.lottie.compose)

    ksp(libs.androidx.room.compiler)
}

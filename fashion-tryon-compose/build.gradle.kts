import com.aiuta.fashionsdk.androidLibrary

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    id("androidx.baselineprofile")
    id("com.android.library")
    id("kotlin-android")
}

androidLibrary(
    name = "com.aiuta.fashionsdk.tryon.compose",
    composeLibrary = true,
)

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
    api(projects.internal.analytic)

    annotationProcessor(libs.androidx.room.compiler)

    baselineProfile(projects.internal.benchmark)

    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.paging.compose)
    implementation(libs.coil.compose)
    implementation(libs.lottie.compose)
    implementation(libs.kotlinx.serialization)
    implementation(libs.ktor.core)

    ksp(libs.androidx.room.compiler)
}

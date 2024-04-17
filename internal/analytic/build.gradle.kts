import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.kotlinx.serialization)
}

androidLibrary(
    name = "com.aiuta.fashionsdk.internal.analytic",
    config = true,
)

dependencies {

    api(projects.fashion)
    api(projects.fashionNetwork)

    implementation(libs.androidx.work)
    implementation(libs.kotlinx.serialization)
    implementation(libs.ktor.core)
}

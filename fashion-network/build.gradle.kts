import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.kotlinx.serialization)
}

androidLibrary(name = "com.aiuta.fashionsdk.network")

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.core)
    implementation(libs.ktor.engine.okhttp)
    implementation(libs.ktor.logging) // TODO DELETE
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.serialization)

    implementation(projects.fashion)
}

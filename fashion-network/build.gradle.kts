import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.kotlinx.serialization)
}

androidLibrary(
    name = "com.aiuta.fashionsdk.network",
    config = true,
)

dependencies {
    api(projects.fashion)

    implementation(libs.kotlinx.atomicfu)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.core)
    implementation(libs.ktor.engine.okhttp)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.serialization)
}

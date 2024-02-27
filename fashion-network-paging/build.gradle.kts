import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.kotlinx.serialization)
}

androidLibrary(name = "com.aiuta.fashionsdk.network.paging")

dependencies {
    api(projects.fashionNetwork)

    implementation(libs.androidx.paging)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)
    implementation(libs.ktor.core)
}

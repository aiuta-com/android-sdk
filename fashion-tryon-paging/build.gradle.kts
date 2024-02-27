import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibrary(name = "com.aiuta.fashionsdk.tryon.paging")

dependencies {
    api(projects.fashionNetworkPaging)
    api(projects.fashionTryonCore)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging)
}

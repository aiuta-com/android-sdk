import com.aiuta.fashionsdk.androidLibrary

plugins {
    id("com.android.library")
    id("kotlin-android")
}

androidLibrary(name = "com.aiuta.fashionsdk.analytic", isNotIncludeToPublish = true) // TODO

dependencies {

    api(projects.internal.analytic)

    implementation(libs.kotlinx.coroutines.core)
}

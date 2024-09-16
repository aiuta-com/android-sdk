import com.aiuta.fashionsdk.androidApplication

plugins {
    id("com.android.application")
    id("kotlin-android")
}

androidApplication(
    name = "sample.tryon",
    composeApp = true,
    shouldBePublic = false,
) {
    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                "./shrinker-rules.pro",
                "./shrinker-rules-android.pro",
            )
            signingConfig = signingConfigs["debug"]
        }
    }
}

dependencies {
    api(projects.fashion)
    api(projects.fashionAnalytic)
    api(projects.fashionComposeIcons)
    api(projects.fashionTryonCore)
    api(projects.fashionTryonCompose)

    implementation(libs.androidx.compose.material)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.material)
}

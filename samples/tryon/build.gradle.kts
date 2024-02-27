import com.aiuta.fashionsdk.androidApplication
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("com.android.application")
    id("org.jetbrains.compose")
    id("kotlin-android")
}

androidApplication(
    name = "sample.tryon",
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
    api(projects.fashionTryonCore)
    api(projects.fashionTryonCompose)

    implementation(compose.material)
    @OptIn(ExperimentalComposeLibrary::class)
    implementation(compose.components.resources)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.material)
}

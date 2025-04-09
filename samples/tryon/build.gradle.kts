import com.aiuta.fashionsdk.androidApplication

plugins {
    id("com.android.application")
    id("kotlin-android")
    alias(libs.plugins.secrets)
}

androidApplication(
    name = "sample.tryon",
    composeApp = true,
) {
    buildFeatures {
        buildConfig = true
    }

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

secrets {
    defaultPropertiesFileName = "secrets.properties"
}

dependencies {
    implementation(projects.fashion)
    implementation(projects.fashionAnalytic)
    implementation(projects.fashionTryonCompose)
    implementation(projects.fashionTryonComposeDefaults)
    implementation(projects.fashionTryonCore)

    implementation(libs.androidx.compose.material)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.material)
}

import com.aiuta.fashionsdk.androidApplicationV2

plugins {
    id("com.android.application")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.secrets)
}

androidApplicationV2(name = "sample.tryon.kmp") {
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

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        // TODO Review deps
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(projects.fashionTryonCompose)
            implementation(projects.fashionTryonComposeDefaults)

            // TODO Delete
            implementation(projects.fashionTryonImages)
            implementation(projects.fashionTryonIcons)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.jetbrains.viewmodel)
        }
    }
}

import com.aiuta.fashionsdk.androidApplication
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.buildKonfig)
}

androidApplication(name = "sample.tryon.kmp") {
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

buildkonfig {
    packageName = "sample.tryon.kmp"

    // Let's load api key from secrets.properties in root of project
    val props = Properties()
    try {
        props.load(file("${rootProject.projectDir.absolutePath}/secrets.properties").inputStream())
    } catch (e: Exception) {
        // keys are private and can not be committed to git
    }

    defaultConfigs {
        buildConfigField(STRING, "AIUTA_API_KEY", props["AIUTA_API_KEY"].toString())
    }
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
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(projects.fashionTryonCompose)
            implementation(projects.fashionTryonComposeDefaults)

            implementation(compose.ui)

            implementation(libs.jetbrains.viewmodel)
        }
    }
}

import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2
import com.aiuta.fashionsdk.versionName
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlinx.serialization)
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.internal.analytic")

//dependencies {
//
//    implementation(libs.ktor.core)
//}

buildkonfig {
    packageName = "com.aiuta.fashionsdk.internal.analytic"
    defaultConfigs {
        buildConfigField(STRING, "VERSION_NAME", versionName)
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.fashion)
                api(projects.fashionNetwork)

                implementation(libs.kotlinx.atomicfu)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)
                implementation(libs.ktor.core)
            }
        }
    }
}

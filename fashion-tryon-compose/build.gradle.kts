import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibraryV2

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.room)
}

addAllMultiplatformTargets()
androidLibraryV2(name = "com.aiuta.fashionsdk.tryon.compose") {
    defaultConfig {
        consumerProguardFiles("shrinker-rules.pro")
    }
}

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.androidx.core)
                implementation(libs.androidx.activity.compose)
                implementation(libs.ktor.engine.okhttp)
            }
        }
        androidUnitTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.mockk)
            }
        }
        commonMain {
            dependencies {
                api(projects.fashionCompose)
                api(projects.fashionTryonCore)
                api(projects.internal.analytic)

                implementation(compose.material)

                implementation(libs.androidx.paging.common)
                implementation(libs.coil3.compose)
                implementation(libs.coil3.network.ktor3)
                implementation(libs.compose.placeholder)
                implementation(libs.haze)
                implementation(libs.kotlinx.atomicfu)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)
                implementation(libs.room.runtime)
                implementation(libs.room.paging)
                implementation(libs.jetbrains.lifecycle)
                implementation(libs.sqlite.bundled)
                implementation(libs.moko.permissions.camera)
                implementation(libs.moko.permissions.gallery)
                implementation(libs.moko.compose)
            }
        }
        appleMain {
            dependencies {
                implementation(libs.ktor.engine.darwin)
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.ui.unit.android)
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

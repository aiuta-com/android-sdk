import com.aiuta.fashionsdk.addAllMultiplatformTargets
import com.aiuta.fashionsdk.androidLibrary
import com.aiuta.fashionsdk.mobileMain

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight)
}

addAllMultiplatformTargets()
androidLibrary(name = "com.aiuta.fashionsdk.tryon.compose") {
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
                implementation(libs.sqldelight.driver.android)
                implementation(libs.androidx.ui.unit.android)
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
                api(projects.fashionComposeCore)
                api(projects.fashionConfiguration)
                api(projects.fashionTryonCore)

                implementation(compose.material)

                implementation(libs.androidx.paging.common)
                implementation(libs.coil3.compose)
                implementation(libs.coil3.network.ktor3)
                implementation(libs.compose.placeholder)
                implementation(libs.haze)
                implementation(libs.kotlinx.atomicfu)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)
                implementation(libs.ksoup.html)
                implementation(libs.ksoup.entities)
                implementation(libs.jetbrains.lifecycle)
                implementation(libs.jetbrains.compose.ui.backhandler)
                implementation(libs.sqlite.bundled)
                implementation(libs.sqldelight.adapters)
                implementation(libs.sqldelight.extension.coroutines)

                implementation(projects.fashionAnalyticsEvents)
                implementation(projects.fashionTryonComposeUikit)
                implementation(projects.internal.internalFashionAnalytics)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.calf.picker)
                implementation(libs.ktor.engine.okhttp)
                implementation(libs.sqldelight.driver.jvm)
            }
        }
        mobileMain {
            dependencies {
                implementation(libs.moko.permissions.camera)
                implementation(libs.moko.permissions.gallery)
                implementation(libs.moko.compose)
            }
        }
        nativeMain {
            dependencies {
                implementation(libs.ktor.engine.darwin)
                implementation(libs.sqldelight.driver.native)
            }
        }
    }
}

sqldelight {
    databases {
        create("AiutaTryOnDatabase") {
            packageName.set("com.aiuta.fashionsdk.tryon.compose.data.internal.database")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight"))
            verifyMigrations.set(true)
            generateAsync.set(true)
        }
    }
}

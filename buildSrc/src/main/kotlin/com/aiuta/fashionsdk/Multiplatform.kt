package com.aiuta.fashionsdk

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.addAllMultiplatformTargets() {
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            applyAiutaHierarchyTemplate()

            val isAndroidApp = plugins.hasPlugin("com.android.application")
            val isAndroidLibrary = plugins.hasPlugin("com.android.library")
            if (isAndroidApp || isAndroidLibrary) {
                androidTarget {
                    if (isAndroidLibrary) {
                        publishLibraryVariants("release")
                    }
                }
            }

            jvm()

            iosX64()
            iosArm64()
            iosSimulatorArm64()
        }
    }
}

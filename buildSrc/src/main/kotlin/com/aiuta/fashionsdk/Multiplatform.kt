package com.aiuta.fashionsdk

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

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

            jvm("desktop")

            iosX64()
            iosArm64()
            iosSimulatorArm64()
        }
    }
}

val NamedDomainObjectContainer<KotlinSourceSet>.desktopMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("desktopMain")

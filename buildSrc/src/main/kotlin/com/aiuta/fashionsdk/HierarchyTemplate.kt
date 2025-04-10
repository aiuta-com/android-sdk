@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

package com.aiuta.fashionsdk

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

private val hierarchyTemplate = KotlinHierarchyTemplate {
    withSourceSetTree(
        KotlinSourceSetTree.main,
        KotlinSourceSetTree.test,
    )

    common {
        withCompilations { true }

        groupNonAndroid()
        groupNative()
    }
}

private fun KotlinHierarchyBuilder.groupNonAndroid() {
    group("nonAndroid") {
        withJvm()
        groupNative()
    }
}

private fun KotlinHierarchyBuilder.groupNative() {
    group("native") {
        withNative()

        group("apple") {
            withApple()

            group("ios") {
                withIos()
            }

            group("macos") {
                withMacos()
            }
        }
    }
}

fun KotlinMultiplatformExtension.applyAiutaHierarchyTemplate() {
    applyHierarchyTemplate(hierarchyTemplate)
}

@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

package com.aiuta.fashionsdk

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

private val hierarchyTemplate =
    KotlinHierarchyTemplate {
        withSourceSetTree(
            KotlinSourceSetTree.main,
            KotlinSourceSetTree.test,
        )

        common {
            withCompilations { true }

            groupJvmCommon()
        }
    }

private fun KotlinHierarchyBuilder.groupJvmCommon() {
    group("jvmCommon") {
        withAndroidTarget()
        withJvm()
    }
}

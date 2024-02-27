import com.aiuta.fashionsdk.enableComposeMetrics
import com.aiuta.fashionsdk.groupId
import com.aiuta.fashionsdk.publicModules
import com.aiuta.fashionsdk.versionName
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessExtensionPredeclare
import kotlinx.validation.ApiValidationExtension
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradlePlugin.android)
        classpath(libs.gradlePlugin.kotlin)
        classpath(libs.gradlePlugin.jetbrainsCompose)
        classpath(libs.gradlePlugin.mavenPublish)
    }
}

plugins {
    alias(libs.plugins.baselineProfile) apply false
    alias(libs.plugins.binaryCompatibility)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.spotless)
}

extensions.configure<ApiValidationExtension> {
    ignoredProjects +=
        project.subprojects.mapNotNull { project ->
            if (project.name in publicModules) null else project.name
        }
}

tasks.withType<DokkaMultiModuleTask>().configureEach {
    outputDirectory = layout.projectDirectory.dir("docs/api")
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    // Necessary to publish to Maven.
    group = groupId
    version = versionName

    // Target JVM 8.
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
        options.compilerArgs = options.compilerArgs + "-Xlint:-options"
    }
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }

    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            jdkVersion = 8
            failOnWarning = true
            skipDeprecated = true
            suppressInheritedMembers = true
        }
    }

    plugins.withId("org.jetbrains.compose") {
        extensions.configure<ComposeExtension> {
            kotlinCompilerPlugin = libs.jetbrains.compose.compiler.get().toString()
        }
    }

    if (enableComposeMetrics && name in publicModules) {
        plugins.withId("org.jetbrains.compose") {
            tasks.withType<KotlinCompile> {
                val outputDir = layout.buildDirectory.dir("composeMetrics").get().asFile.path
                compilerOptions.freeCompilerArgs.addAll(
                    "-P",
                    "$composePlugin:metricsDestination=$outputDir",
                    "-P",
                    "$composePlugin:reportsDestination=$outputDir",
                )
            }
        }
    }

    apply(plugin = "com.diffplug.spotless")

    val configureSpotless: SpotlessExtension.() -> Unit = {
        kotlin {
            target("**/*.kt", "**/*.kts")
            ktlint(
                libs.versions.ktlint.get(),
            ).setEditorConfigPath("${project.rootDir}/.editorconfig")
            endWithNewline()
            indentWithSpaces()
            trimTrailingWhitespace()
        }
    }

    if (project === rootProject) {
        spotless { predeclareDeps() }
        extensions.configure<SpotlessExtensionPredeclare>(configureSpotless)
    } else {
        extensions.configure<SpotlessExtension>(configureSpotless)
    }
}

private val composePlugin = "plugin:androidx.compose.compiler.plugins.kotlin"

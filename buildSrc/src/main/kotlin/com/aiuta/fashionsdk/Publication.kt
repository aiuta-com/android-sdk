package com.aiuta.fashionsdk

import com.android.build.api.dsl.ApplicationPublishing
import com.android.build.api.dsl.LibraryPublishing
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.jreleaser.gradle.plugin.JReleaserExtension

fun <T : BaseExtension> Project.setupPublishing() {
    apply(plugin = "maven-publish")
    apply(plugin = "org.jreleaser")

//    android<T> {
//        extensions.configure<LibraryPublishing> {
//            singleVariant("release") {
//                withSourcesJar()
//                withJavadocJar()
//            }
//        }
//    }

    extensions.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("release") {
                afterEvaluate {
                    from(components["release"])
                }
            }
        }

        repositories {
            maven {
                setUrl(layout.buildDirectory.dir("staging-deploy"))
            }
        }
    }

    configureJReleaser()
}

private fun Project.configureJReleaser() {
    extensions.configure<JReleaserExtension> {

        gitRootSearch = true

        release {
            github {
                skipRelease = true
                skipTag = true
//                sign = true
//                branch = "master"
//                branchPush = "master"
//                overwrite = true
            }
        }
    }
}

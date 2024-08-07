package com.aiuta.fashionsdk

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
import org.jreleaser.model.Active
import com.aiuta.fashionsdk.groupId as currentGroupId
import com.aiuta.fashionsdk.artifactId as currentArtifactId

inline fun <reified T : LibraryExtension> Project.setupBomPublishing() {
    apply(plugin = "maven-publish")
    apply(plugin = "org.jreleaser")

    createJReleaserDirectory()

    configureMavenPublication(isAndroidLibrary = false)
    configureJReleaser()
}

internal inline fun <reified T : LibraryExtension> Project.setupAndroidPublishing() {
    apply(plugin = "maven-publish")
    apply(plugin = "org.jreleaser")

    createJReleaserDirectory()

    android<T> {
        extensions.configure<T> {
            publishing {
                singleVariant("release") {
                    withSourcesJar()
                    withJavadocJar()
                }
            }
        }
    }

    configureMavenPublication(isAndroidLibrary = true)

    configureJReleaser()
}

fun Project.configureMavenPublication(
    isAndroidLibrary: Boolean,
) {
    extensions.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("release") {
                if (isAndroidLibrary) {
                    afterEvaluate {
                        from(components["release"])
                    }
                }

                groupId = currentGroupId
                artifactId = currentArtifactId

                pom {
                    name.set(project.pomName)
                    description.set(project.pomDescription)
                    url.set(project.pomUrl)

                    issueManagement {
                        url.set(project.pomUrlIssue)
                    }

                    scm {
                        url.set(project.scmUrl)
                        connection.set(project.scmConnection)
                        developerConnection.set(project.scmDevConnection)
                    }

                    licenses {
                        license {
                            name.set(project.licenceName)
                            url.set(project.licenceUrl)
                            distribution.set(project.licenceDist)
                        }
                    }

                    developers {
                        developer {
                            id.set("MishkaV")
                            name.set("Mikhail Vorozhtcov")
                            email.set("mike.vorozhtcov@aiuta.com")
                        }
                    }
                }
            }
        }

        repositories {
            maven {
                setUrl(layout.buildDirectory.dir("staging-deploy"))
            }
        }
    }
}

fun Project.configureJReleaser() {
    extensions.configure<JReleaserExtension> {
        project {
            inceptionYear = "2024"
            author("@MishkaV")
        }

        gitRootSearch = true

        signing {
            active = Active.ALWAYS
            armored = true
            verify = true
        }

        release {
            github {
                skipRelease = true
                skipTag = true
                sign = true
                branch = "main"
                branchPush = "main"
                overwrite = true
            }
        }

        deploy {
            maven {
                mavenCentral.create("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                    setAuthorization("Basic")
                    applyMavenCentralRules =
                        false // Wait for fix: https://github.com/kordamp/pomchecker/issues/21
                    sign = true
                    checksums = true
                    sourceJar = true
                    javadocJar = true
                    retryDelay = 60
                }
            }
        }
    }
}

/**
 * JRelease can't create build/jreleaser directory, that is why let's
 * try create it on our side
 */
fun Project.createJReleaserDirectory() {
    val jreleaserDir = layout.buildDirectory.dir("jreleaser").get().asFile
    if (!jreleaserDir.exists()) {
        jreleaserDir.mkdirs()
        println("Created directory: ${jreleaserDir.absolutePath}")
    }
}

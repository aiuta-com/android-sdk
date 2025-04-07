pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "aiuta-sdk"

// Need for generate project accessors in deps
// https://docs.gradle.org/7.4/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Private modules
include(
    ":internal:benchmark",
    ":samples:tryon",
    ":samples:tryon-kmp",
)

// Public modules
include(
    ":internal:analytic", // TODO Migrate to public
    ":fashion",
    ":fashion-analytic",
    ":fashion-bom",
    ":fashion-network",
    ":fashion-network-paging",
    ":fashion-tryon-compose",
    ":fashion-tryon-compose-configuration",
    ":fashion-tryon-compose-configuration-ui",
    ":fashion-tryon-compose-defaults",
    ":fashion-tryon-compose-defaults-icons",
    ":fashion-tryon-compose-defaults-images",
    ":fashion-tryon-compose-resources",
    ":fashion-tryon-compose-uikit",
    ":fashion-tryon-core",
    ":fashion-tryon-paging",
)

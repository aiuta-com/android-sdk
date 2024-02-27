package com.aiuta.fashionsdk

import kotlin.math.pow
import org.gradle.api.Project

val Project.minSdk: Int
    get() = intProperty("minSdk")

val Project.targetSdk: Int
    get() = intProperty("targetSdk")

val Project.compileSdk: Int
    get() = intProperty("compileSdk")

val Project.groupId: String
    get() = stringProperty("POM_GROUP_ID")

val Project.versionName: String
    get() = stringProperty("POM_VERSION")

val Project.versionCode: Int
    get() =
        versionName
            .takeWhile { it.isDigit() || it == '.' }
            .split('.')
            .map { it.toInt() }
            .reversed()
            .sumByIndexed { index, unit ->
                // 3.2.1 -> 302010
                (unit * 10.0.pow(2 * index + 1)).toInt()
            }

// For checking compose metrics use this add -PenableComposeMetrics=true
val Project.enableComposeMetrics: Boolean
    get() = booleanProperty("enableComposeMetrics") { false }

private fun Project.intProperty(
    name: String,
    default: () -> Int = { error("unknown property: $name") },
): Int = (properties[name] as String?)?.toInt() ?: default()

private fun Project.stringProperty(
    name: String,
    default: () -> String = { error("unknown property: $name") },
): String = (properties[name] as String?) ?: default()

private fun Project.booleanProperty(
    name: String,
    default: () -> Boolean = { error("unknown property: $name") },
): Boolean = (properties[name] as String?)?.toBooleanStrict() ?: default()

private inline fun <T> List<T>.sumByIndexed(selector: (Int, T) -> Int): Int {
    var index = 0
    var sum = 0
    for (element in this) {
        sum += selector(index++, element)
    }
    return sum
}

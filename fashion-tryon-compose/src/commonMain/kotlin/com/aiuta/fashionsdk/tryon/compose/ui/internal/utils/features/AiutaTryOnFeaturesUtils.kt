package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features

internal fun <T> checkFeatureAvailability(
    name: String,
    feature: T?,
): T {
    return checkNotNull(feature) {
        "Feature $name is not initialized. Please, init it with AiutaTryOnConfiguration instance"
    }
}

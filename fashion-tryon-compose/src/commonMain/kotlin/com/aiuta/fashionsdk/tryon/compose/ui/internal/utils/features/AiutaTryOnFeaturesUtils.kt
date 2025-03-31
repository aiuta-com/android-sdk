package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features

import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.AiutaTryOnFeature

internal fun <T : AiutaTryOnFeature> checkFeatureAvailability(
    name: String,
    feature: T?,
): T {
    return checkNotNull(feature) {
        "Feature $name is not initialized. Please, init it with AiutaTryOnConfiguration instance"
    }
}

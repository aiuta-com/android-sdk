package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data

import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.FeedbackFeatureUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.FitDisclaimerFeatureUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toUiModel

internal suspend fun AiutaTryOnDataController.preloadConfig() {
    try {
        configRepository.loadConfig()
    } catch (e: Exception) {
        // Failed to preload config
    }
}

internal suspend fun AiutaTryOnDataController.providePoweredByUrl(): String? {
    return try {
        configRepository.getPoweredByStickerFeature()?.urlAndroid
    } catch (e: Exception) {
        // Failed to solve powered by url
        null
    }
}

internal suspend fun AiutaTryOnDataController.provideFeedbackFeature(): FeedbackFeatureUiModel? {
    return try {
        configRepository.getFeedbackFeature()?.toUiModel()
    } catch (e: Exception) {
        // Failed to solve feedback feature
        null
    }
}

internal suspend fun AiutaTryOnDataController.provideFitDisclaimerFeature(): FitDisclaimerFeatureUiModel? {
    return try {
        configRepository.getFitDisclaimerFeature()?.toUiModel()
    } catch (e: Exception) {
        // Failed to solve fit disclaimer
        null
    }
}

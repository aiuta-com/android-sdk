package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data

import com.aiuta.fashionsdk.logger.d
import com.aiuta.fashionsdk.logger.e
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.TryOnModelsCategoryUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toUiModel

internal suspend fun AiutaTryOnDataController.preloadConfig() {
    try {
        logger?.d("preloadConfig(): Start preloading config")
        configRepository.loadConfig()
    } catch (e: Exception) {
        // Failed to preload config
        logger?.e("preloadConfig(): Failed to preload config with exception - $e")
    }
}

internal suspend fun AiutaTryOnDataController.provideTryOnModelsCategories(
    predefinedModelCategories: Map<String, String>,
    forceUpdate: Boolean = false,
): Result<List<TryOnModelsCategoryUiModel>?> = kotlin.runCatching {
    configRepository
        .getTryOnModelsCategories(forceUpdate)
        ?.mapNotNull { category ->
            if (category.models.isNotEmpty()) {
                category.toUiModel(predefinedModelCategories)
            } else {
                // Let's skip empty category
                null
            }
        }
}

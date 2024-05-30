package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data

internal suspend fun AiutaTryOnDataController.preloadConfig() {
    try {
        configRepository.loadConfig()
    } catch (e: Exception) {
        // Failed to preload config
    }
}

internal suspend fun AiutaTryOnDataController.providePoweredByUrl(): String? {
    return try {
        val poweredByStickerFeature = configRepository.getPoweredByStickerFeature()

        if (poweredByStickerFeature.enabled) {
            poweredByStickerFeature.urlAndroid
        } else {
            null
        }
    } catch (e: Exception) {
        // Failed to solve powered by url
        null
    }
}

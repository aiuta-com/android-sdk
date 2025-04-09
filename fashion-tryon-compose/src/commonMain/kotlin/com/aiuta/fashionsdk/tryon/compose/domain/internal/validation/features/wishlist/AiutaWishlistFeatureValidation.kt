package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.wishlist

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

internal fun AiutaWishlistFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    strings.wishlistButtonAdd.validateStringWithSettings(
        propertyName = "wishlistButtonAdd",
        logger = logger,
        debugSettings = debugSettings,
    )
}

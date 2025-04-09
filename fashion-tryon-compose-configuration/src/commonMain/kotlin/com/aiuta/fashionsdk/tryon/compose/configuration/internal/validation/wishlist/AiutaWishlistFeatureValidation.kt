package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.wishlist

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringWithSettings

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

package com.aiuta.fashionsdk.configuration.internal.validation.features.wishlist

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

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

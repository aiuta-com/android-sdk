package com.aiuta.fashionsdk.tryon.compose.defaults

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.aiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.features
import com.aiuta.fashionsdk.tryon.compose.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.tryon.compose.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.tryon.compose.defaults.features.selector.defaultImageSelector
import com.aiuta.fashionsdk.tryon.compose.defaults.features.share.defaultShare
import com.aiuta.fashionsdk.tryon.compose.defaults.features.tryon.defaultTryOn
import com.aiuta.fashionsdk.tryon.compose.defaults.features.welcome.defaultWelcomeScreen
import com.aiuta.fashionsdk.tryon.compose.defaults.features.wishlist.defaultWishlist

public inline fun defaultAiutaTryOnConfiguration(
    aiuta: Aiuta,
): AiutaTryOnConfiguration = aiutaTryOnConfiguration {
    this.aiuta = aiuta
    features {
        defaultWelcomeScreen()
        defaultOnboarding()
        defaultConsent()
        defaultImageSelector()
        defaultTryOn()
        defaultShare()
        defaultWishlist()
    }
}

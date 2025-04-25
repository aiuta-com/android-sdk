package com.aiuta.fashionsdk.tryon.compose.defaults.features

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.features.features
import com.aiuta.fashionsdk.tryon.compose.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.tryon.compose.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.tryon.compose.defaults.features.selector.defaultImageSelector
import com.aiuta.fashionsdk.tryon.compose.defaults.features.share.defaultShare
import com.aiuta.fashionsdk.tryon.compose.defaults.features.tryon.defaultTryOn
import com.aiuta.fashionsdk.tryon.compose.defaults.features.welcome.defaultWelcomeScreen
import com.aiuta.fashionsdk.tryon.compose.defaults.features.wishlist.defaultWishlist

public fun AiutaConfiguration.Builder.defaultAiutaFeatures(): AiutaConfiguration.Builder = apply {
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

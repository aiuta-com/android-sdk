package com.aiuta.fashionsdk.configuration.defaults.features

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.configuration.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.configuration.defaults.features.selector.defaultImageSelector
import com.aiuta.fashionsdk.configuration.defaults.features.share.defaultShare
import com.aiuta.fashionsdk.configuration.defaults.features.tryon.defaultTryOn
import com.aiuta.fashionsdk.configuration.defaults.features.welcome.defaultWelcomeScreen
import com.aiuta.fashionsdk.configuration.defaults.features.wishlist.defaultWishlist
import com.aiuta.fashionsdk.configuration.features.features

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

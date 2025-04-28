package com.aiuta.fashionsdk.configuration.defaults.features

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.configuration.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.configuration.defaults.features.selector.defaultImagePicker
import com.aiuta.fashionsdk.configuration.defaults.features.share.defaultShare
import com.aiuta.fashionsdk.configuration.defaults.features.tryon.defaultTryOn
import com.aiuta.fashionsdk.configuration.defaults.features.welcome.defaultWelcomeScreen
import com.aiuta.fashionsdk.configuration.features.features
import com.aiuta.fashionsdk.configuration.features.tryon.cart.handler.AiutaTryOnCartFeatureHandler

public fun AiutaConfiguration.Builder.defaultAiutaFeatures(
    termsOfServiceUrl: String,
    cartHandler: AiutaTryOnCartFeatureHandler,
): AiutaConfiguration.Builder = features {
    defaultWelcomeScreen()
    defaultOnboarding()
    defaultConsent(termsOfServiceUrl = termsOfServiceUrl)
    defaultImagePicker()
    defaultTryOn(cartHandler = cartHandler)
    defaultShare()
}

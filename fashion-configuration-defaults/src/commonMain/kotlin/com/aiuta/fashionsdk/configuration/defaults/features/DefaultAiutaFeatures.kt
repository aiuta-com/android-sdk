package com.aiuta.fashionsdk.configuration.defaults.features

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.configuration.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.configuration.defaults.features.selector.defaultImagePicker
import com.aiuta.fashionsdk.configuration.defaults.features.share.defaultShare
import com.aiuta.fashionsdk.configuration.defaults.features.tryon.defaultTryOn
import com.aiuta.fashionsdk.configuration.features.features
import com.aiuta.fashionsdk.configuration.features.tryon.cart.handler.AiutaTryOnCartFeatureHandler

/**
 * Configures the default features for the Aiuta SDK.
 *
 * This function sets up the default features including onboarding, consent, image picker, try-on, and share.
 *
 * @param termsOfServiceUrl The URL for the terms of service.
 * @param cartHandler The handler for the try-on cart feature.
 * @return The updated [AiutaConfiguration.Builder] instance.
 */
public fun AiutaConfiguration.Builder.defaultAiutaFeatures(
    termsOfServiceUrl: String,
    cartHandler: AiutaTryOnCartFeatureHandler,
): AiutaConfiguration.Builder = features {
    defaultOnboarding()
    defaultConsent(termsOfServiceUrl = termsOfServiceUrl)
    defaultImagePicker()
    defaultTryOn(cartHandler = cartHandler)
    defaultShare()
}

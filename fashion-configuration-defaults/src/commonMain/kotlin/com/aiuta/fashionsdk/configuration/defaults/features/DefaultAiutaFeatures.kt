package com.aiuta.fashionsdk.configuration.defaults.features

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.configuration.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.configuration.defaults.features.poweredby.defaultPoweredBy
import com.aiuta.fashionsdk.configuration.defaults.features.selector.defaultImagePicker
import com.aiuta.fashionsdk.configuration.defaults.features.share.defaultShare
import com.aiuta.fashionsdk.configuration.defaults.features.tryon.defaultTryOn
import com.aiuta.fashionsdk.configuration.defaults.features.welcome.defaultWelcomeScreen
import com.aiuta.fashionsdk.configuration.features.features

public fun AiutaConfiguration.Builder.defaultAiutaFeatures(): AiutaConfiguration.Builder = apply {
    features {
        defaultWelcomeScreen()
        defaultOnboarding()
        defaultConsent()
        defaultImagePicker()
        defaultTryOn()
        defaultPoweredBy()
        defaultShare()
    }
}

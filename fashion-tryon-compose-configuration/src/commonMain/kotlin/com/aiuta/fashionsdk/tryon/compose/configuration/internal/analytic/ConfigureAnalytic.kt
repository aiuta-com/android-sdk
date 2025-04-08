package com.aiuta.fashionsdk.tryon.compose.configuration.internal.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.JWTAuthenticationStrategy
import com.aiuta.fashionsdk.internal.analytic.model.ConfigureEvent
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.meta.AiutaMode

internal fun AiutaTryOnConfiguration.sendConfigurationEvent() {
    aiutaAnalytic.sendEvent(
        event =
        ConfigureEvent(
            mode = hostMetadata.mode.toSDKMode(),
            authenticationType = aiuta.toAuthenticationType(),
            isHistoryEnable = features.isFeatureInitialize<AiutaTryOnGenerationsHistoryFeature>(),
            isWishlistAvailable = features.isFeatureInitialize<AiutaWishlistFeature>(),
            isPreOnboardingAvailable = features.isFeatureInitialize<AiutaWelcomeScreenFeature>(),
            isShareAvailable = features.isFeatureInitialize<AiutaShareFeature>(),
            isHostDataProviderEnabled = false, // TODO Refactor property
        ),
    )
}

private fun AiutaMode.toSDKMode(): ConfigureEvent.SDKMode = when (this) {
    AiutaMode.FULL_SCREEN -> ConfigureEvent.SDKMode.FULL_SCREEN
    AiutaMode.BOTTOM_SHEET -> ConfigureEvent.SDKMode.BOTTOM_SHEET
}

private fun Aiuta.toAuthenticationType(): ConfigureEvent.AuthenticationType = when (authenticationStrategy) {
    is ApiKeyAuthenticationStrategy -> ConfigureEvent.AuthenticationType.API_KEY
    is JWTAuthenticationStrategy -> ConfigureEvent.AuthenticationType.JWT
}

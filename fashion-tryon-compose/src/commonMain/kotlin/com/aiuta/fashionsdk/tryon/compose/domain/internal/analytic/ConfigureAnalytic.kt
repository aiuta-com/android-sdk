package com.aiuta.fashionsdk.tryon.compose.domain.internal.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.JWTAuthenticationStrategy
import com.aiuta.fashionsdk.internal.analytic.model.ConfigureEvent
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.meta.AiutaMode
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaConfiguration

internal fun AiutaConfiguration.sendConfigurationEvent() {
    tryOnConfiguration.aiutaAnalytic.sendEvent(
        event = ConfigureEvent(
            mode = userInterface.styleMetaData?.mode?.toSDKMode() ?: ConfigureEvent.SDKMode.FULL_SCREEN,
            authenticationType = tryOnConfiguration.aiuta.toAuthenticationType(),
            isHistoryEnable = tryOnConfiguration.features.isFeatureInitialize<AiutaTryOnGenerationsHistoryFeature>(),
            isWishlistAvailable = tryOnConfiguration.features.isFeatureInitialize<AiutaWishlistFeature>(),
            isPreOnboardingAvailable = tryOnConfiguration.features.isFeatureInitialize<AiutaWelcomeScreenFeature>(),
            isShareAvailable = tryOnConfiguration.features.isFeatureInitialize<AiutaShareFeature>(),
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

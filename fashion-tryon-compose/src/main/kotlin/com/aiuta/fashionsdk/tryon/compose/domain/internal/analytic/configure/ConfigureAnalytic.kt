package com.aiuta.fashionsdk.tryon.compose.domain.internal.analytic.configure

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.JWTAuthenticationStrategy
import com.aiuta.fashionsdk.internal.analytic.model.ConfigureEvent
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.meta.AiutaMode

internal fun AiutaTryOnConfiguration.sendConfigurationEvent() {
    aiutaAnalytic.sendEvent(
        event =
            ConfigureEvent(
                pageId = null,
                productId = skuForGeneration.skuId,
                mode = hostMetadata.mode.toSDKMode(),
                authenticationType = aiuta.toAuthenticationType(),
                isHistoryEnable = toggles.isHistoryAvailable,
                isWishlistAvailable = toggles.isWishlistAvailable,
                isPreOnboardingAvailable = toggles.isPreOnboardingAvailable,
                isShareAvailable = toggles.isShareAvailable,
                isHostDataProviderEnabled = dataProvider != null,
            ),
    )
}

private fun AiutaMode.toSDKMode(): ConfigureEvent.SDKMode {
    return when (this) {
        AiutaMode.FULL_SCREEN -> ConfigureEvent.SDKMode.FULL_SCREEN
        AiutaMode.BOTTOM_SHEET -> ConfigureEvent.SDKMode.BOTTOM_SHEET
    }
}

private fun Aiuta.toAuthenticationType(): ConfigureEvent.AuthenticationType {
    return when (authenticationStrategy) {
        is ApiKeyAuthenticationStrategy -> ConfigureEvent.AuthenticationType.API_KEY
        is JWTAuthenticationStrategy -> ConfigureEvent.AuthenticationType.JWT
    }
}

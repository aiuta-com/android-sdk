package com.aiuta.fashionsdk.configuration.internal.analytic

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.JWTAuthenticationStrategy
import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature
import com.aiuta.fashionsdk.configuration.features.picker.camera.AiutaImagePickerCameraFeature
import com.aiuta.fashionsdk.configuration.features.picker.history.AiutaImagePickerUploadsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.picker.model.AiutaImagePickerPredefinedModelFeature
import com.aiuta.fashionsdk.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.configuration.features.share.watermark.AiutaShareWatermarkFeature
import com.aiuta.fashionsdk.configuration.features.tryon.disclaimer.AiutaTryOnFitDisclaimerFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.AiutaTryOnFeedbackOtherFeature
import com.aiuta.fashionsdk.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.tryon.other.AiutaTryOnWithOtherPhotoFeature
import com.aiuta.fashionsdk.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.configuration.ui.meta.AiutaMode
import com.aiuta.fashionsdk.internal.analytic.model.ConfigureEvent

internal fun AiutaConfiguration.sendConfigurationEvent() {
    aiutaAnalytic.sendEvent(
        event = ConfigureEvent(
            mode = userInterface.styleMetaData?.mode?.toSDKMode()
                ?: ConfigureEvent.SDKMode.FULL_SCREEN,
            authenticationType = aiuta.toAuthenticationType(),
            welcomeScreenFeatureEnabled = features.isFeatureInitialize<AiutaWelcomeScreenFeature>(),
            onboardingFeatureEnabled = features.isFeatureInitialize<AiutaOnboardingFeature>(),
            onboardingBestResultsPageFeatureEnabled = features.isFeatureInitialize<AiutaOnboardingBestResultsPageFeature>(),
            consentFeatureEnabled = features.isFeatureInitialize<AiutaConsentFeature>(),
            imagePickerCameraFeatureEnabled = features.isFeatureInitialize<AiutaImagePickerCameraFeature>(),
            imagePickerPredefinedModelFeatureEnabled = features.isFeatureInitialize<AiutaImagePickerPredefinedModelFeature>(),
            imagePickerUploadsHistoryFeatureEnabled = features.isFeatureInitialize<AiutaImagePickerUploadsHistoryFeature>(),
            tryOnFitDisclaimerFeatureEnabled = features.isFeatureInitialize<AiutaTryOnFitDisclaimerFeature>(),
            tryOnFeedbackFeatureEnabled = features.isFeatureInitialize<AiutaTryOnFeedbackFeature>(),
            tryOnFeedbackOtherFeatureEnabled = features.isFeatureInitialize<AiutaTryOnFeedbackOtherFeature>(),
            tryOnGenerationsHistoryFeatureEnabled = features.isFeatureInitialize<AiutaTryOnGenerationsHistoryFeature>(),
            tryOnMultiItemFeatureEnabled = false,
            tryOnWithOtherPhotoFeatureEnabled = features.isFeatureInitialize<AiutaTryOnWithOtherPhotoFeature>(),
            shareFeatureEnabled = features.isFeatureInitialize<AiutaShareFeature>(),
            shareWatermarkFeatureEnabled = features.isFeatureInitialize<AiutaShareWatermarkFeature>(),
            wishlistFeatureEnabled = features.isFeatureInitialize<AiutaWishlistFeature>(),
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

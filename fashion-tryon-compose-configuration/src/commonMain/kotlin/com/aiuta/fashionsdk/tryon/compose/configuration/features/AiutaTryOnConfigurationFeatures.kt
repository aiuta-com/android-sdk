package com.aiuta.fashionsdk.tryon.compose.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.exceptions.NoSuchFeatureException
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.AiutaImageSelectorCameraFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.AiutaImageSelectorPhotoGalleryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.AiutaImageSelectorUploadsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.AiutaImageSelectorPredefinedModelFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaTryOnConfigurationFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
    public val onboarding: AiutaOnboardingFeature?,
    public val consent: AiutaConsentFeature?,
    public val imageSelector: AiutaImageSelectorFeature,
    public val tryOn: AiutaTryOnFeature,
) {
    public inline fun <reified T : AiutaTryOnConfigurationFeature> provideFeature(): T? = when (T::class) {
        // Welcome
        AiutaWelcomeScreenFeature::class -> welcomeScreen
        // Onboarding
        AiutaOnboardingFeature::class -> onboarding
        AiutaOnboardingTryOnPageFeature::class -> onboarding?.tryOnPage
        AiutaOnboardingBestResultsPageFeature::class -> onboarding?.bestResultsPage
        // Consent
        AiutaConsentFeature::class -> consent
        AiutaConsentStandaloneOnboardingPageFeature::class -> consent
        AiutaConsentBuiltInWithOnboardingPageFeature::class -> consent
        // Image selector
        AiutaImageSelectorFeature::class -> imageSelector
        AiutaImageSelectorCameraFeature::class -> imageSelector.camera
        AiutaImageSelectorPhotoGalleryFeature::class -> imageSelector.photoGallery
        AiutaImageSelectorPredefinedModelFeature::class -> imageSelector.predefinedModels
        AiutaImageSelectorUploadsHistoryFeature::class -> imageSelector.uploadsHistory
        // Try on
        AiutaTryOnFeature::class -> tryOn
        else -> throw NoSuchFeatureException(T::class.qualifiedName)
    } as? T

    public inline fun <reified T : AiutaTryOnConfigurationFeature> strictProvideFeature(): T = checkNotNull(provideFeature()) {
        """
                Feature ${T::class.qualifiedName} is not initialized, therefore we can't provide it.
                Please, don't use it or create instance of AiutaTryOnConfigurationFeatures with this feature.
        """.trimIndent()
    }

    public inline fun <reified T : AiutaTryOnConfigurationFeature> isFeatureInitialize(): Boolean = provideFeature<T>() != null

    @AiutaDsl
    public class Builder {
        public var welcomeScreen: AiutaWelcomeScreenFeature? = null
        public var onboarding: AiutaOnboardingFeature? = null
        public var consent: AiutaConsentFeature? = null
        public var imageSelector: AiutaImageSelectorFeature? = null
        public var tryOn: AiutaTryOnFeature? = null

        public fun build(): AiutaTryOnConfigurationFeatures {
            val parentClass = "AiutaTryOnConfigurationFeatures"
            return AiutaTryOnConfigurationFeatures(
                welcomeScreen = welcomeScreen,
                onboarding = onboarding,
                consent = consent,
                imageSelector = imageSelector.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "imageSelector",
                ),
                tryOn = tryOn.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "tryOn",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnConfiguration.Builder.features(
    block: AiutaTryOnConfigurationFeatures.Builder.() -> Unit,
): AiutaTryOnConfiguration.Builder = apply {
    features = AiutaTryOnConfigurationFeatures.Builder().apply(block).build()
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.exceptions.NoSuchFeatureException
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.AiutaImageSelectorCamera
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.AiutaImageSelectorPhotoGallery
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.AiutaImageSelectorUploadsHistory
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.AiutaImageSelectorPredefinedModel
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaTryOnFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
    public val onboarding: AiutaOnboardingFeature?,
    public val consent: AiutaConsentFeature?,
    public val imageSelector: AiutaImageSelectorFeature,
) {
    public inline fun <reified T : AiutaFeature> provideFeature(): T? = when (T::class) {
        // Welcome
        AiutaWelcomeScreenFeature::class -> welcomeScreen
        // Onboarding
        AiutaOnboardingFeature::class -> onboarding
        AiutaOnboardingTryOnPage::class -> onboarding?.tryOnPage
        AiutaOnboardingBestResultsPage::class -> onboarding?.bestResultsPage
        // Consent
        AiutaConsentFeature::class -> consent
        // Image selector
        AiutaImageSelectorFeature::class -> imageSelector
        AiutaImageSelectorCamera::class -> imageSelector.camera
        AiutaImageSelectorPhotoGallery::class -> imageSelector.photoGallery
        AiutaImageSelectorPredefinedModel::class -> imageSelector.predefinedModels
        AiutaImageSelectorUploadsHistory::class -> imageSelector.uploadsHistory
        else -> throw NoSuchFeatureException()
    } as? T

    public inline fun <reified T : AiutaFeature> strictProvideFeature(): T = checkNotNull(provideFeature()) {
        """
                Feature ${T::class.qualifiedName} is not initialized, therefore we can't provide it.
                Please, don't use it or create instance of AiutaTryOnFeatures with this feature.
        """.trimIndent()
    }

    public inline fun <reified T : AiutaFeature> isFeatureInitialize(): Boolean = provideFeature<T>() != null

    @AiutaDsl
    public class Builder {
        public var welcomeScreen: AiutaWelcomeScreenFeature? = null
        public var onboarding: AiutaOnboardingFeature? = null
        public var consent: AiutaConsentFeature? = null
        public var imageSelector: AiutaImageSelectorFeature? = null

        public fun build(): AiutaTryOnFeatures {
            val parentClass = "AiutaTryOnFeatures"
            return AiutaTryOnFeatures(
                welcomeScreen = welcomeScreen,
                onboarding = onboarding,
                consent = consent,
                imageSelector = imageSelector.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "imageSelector",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnConfiguration.Builder.aiutaTryOnFeatures(
    block: AiutaTryOnFeatures.Builder.() -> Unit,
) {
    features = AiutaTryOnFeatures.Builder().apply(block).build()
}

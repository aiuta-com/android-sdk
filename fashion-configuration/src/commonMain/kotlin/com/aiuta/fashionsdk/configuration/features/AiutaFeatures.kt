package com.aiuta.fashionsdk.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.builtin.AiutaConsentBuiltInWithOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.exceptions.NoSuchFeatureException
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPageFeature
import com.aiuta.fashionsdk.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.configuration.features.selector.camera.AiutaImageSelectorCameraFeature
import com.aiuta.fashionsdk.configuration.features.selector.gallery.AiutaImageSelectorPhotoGalleryFeature
import com.aiuta.fashionsdk.configuration.features.selector.history.AiutaImageSelectorUploadsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.selector.model.AiutaImageSelectorPredefinedModelFeature
import com.aiuta.fashionsdk.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.configuration.features.share.watermark.AiutaShareWatermarkFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.disclaimer.AiutaTryOnFitDisclaimerFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.AiutaTryOnFeedbackOtherFeature
import com.aiuta.fashionsdk.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.tryon.loading.AiutaTryOnLoadingPageFeature
import com.aiuta.fashionsdk.configuration.features.tryon.repicking.AiutaTryOnWithOtherPhotoFeature
import com.aiuta.fashionsdk.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

@Immutable
public class AiutaFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
    public val onboarding: AiutaOnboardingFeature?,
    public val consent: AiutaConsentFeature?,
    public val imageSelector: AiutaImageSelectorFeature,
    public val tryOn: AiutaTryOnFeature,
    public val share: AiutaShareFeature?,
    public val wishlist: AiutaWishlistFeature?,
) {
    public inline fun <reified T : AiutaFeature> provideFeature(): T? = when (T::class) {
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
        AiutaTryOnLoadingPageFeature::class -> tryOn.loadingPage
        AiutaTryOnFitDisclaimerFeature::class -> tryOn.fitDisclaimer
        AiutaTryOnFeedbackFeature::class -> tryOn.feedback
        AiutaTryOnFeedbackOtherFeature::class -> tryOn.feedback?.otherFeedback
        AiutaTryOnGenerationsHistoryFeature::class -> tryOn.generationsHistory
        AiutaTryOnWithOtherPhotoFeature::class -> tryOn.repicking
        // Share
        AiutaShareFeature::class -> share
        AiutaShareWatermarkFeature::class -> share?.watermark
        // Wishlist
        AiutaWishlistFeature::class -> wishlist
        else -> throw NoSuchFeatureException(T::class.qualifiedName)
    } as? T

    public inline fun <reified T : AiutaFeature> strictProvideFeature(): T = checkNotNull(provideFeature()) {
        """
                Feature ${T::class.qualifiedName} is not initialized, therefore we can't provide it.
                Please, don't use it or create instance of AiutaFeatures with this feature.
        """.trimIndent()
    }

    public inline fun <reified T : AiutaFeature> isFeatureInitialize(): Boolean = provideFeature<T>() != null

    @AiutaDsl
    public class Builder {
        public var welcomeScreen: AiutaWelcomeScreenFeature? = null
        public var onboarding: AiutaOnboardingFeature? = null
        public var consent: AiutaConsentFeature? = null
        public var imageSelector: AiutaImageSelectorFeature? = null
        public var tryOn: AiutaTryOnFeature? = null
        public var share: AiutaShareFeature? = null
        public var wishlist: AiutaWishlistFeature? = null

        public fun build(): AiutaFeatures {
            val parentClass = "AiutaFeatures"
            return AiutaFeatures(
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
                share = share,
                wishlist = wishlist,
            )
        }
    }
}

public inline fun AiutaConfiguration.Builder.features(
    block: AiutaFeatures.Builder.() -> Unit,
): AiutaConfiguration.Builder = apply {
    features = AiutaFeatures.Builder().apply(block).build()
}

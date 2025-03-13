package com.aiuta.fashionsdk.compose.tokens.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.images.onboarding.AiutaOnboardingImages

/**
 * Represents the images used in the Aiuta fashion try-on application.
 */
@Immutable
public class AiutaImages(
    // Pre onboarding screen
    public val preonboardingImage: AiutaImage?,
    // Onboarding screen
    public val onboardingImages: AiutaOnboardingImages,
    // Selector screen
    @Deprecated("Will not be used in new version of SDK")
    public val selectorEmptyImage: AiutaImage,
    public val selectorEmptySmallImage1: AiutaImage,
    public val selectorEmptySmallImage2: AiutaImage,
    // Result screen
    public val feedbackThanksImage: AiutaImage,
)

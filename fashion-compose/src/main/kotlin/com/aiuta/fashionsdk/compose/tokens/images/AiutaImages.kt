package com.aiuta.fashionsdk.compose.tokens.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.images.onboarding.AiutaOnboadringImages

/**
 * Represents the images used in the Aiuta fashion try-on application.
 */
@Immutable
public class AiutaImages(
    // Pre onboarding screen
    public val preonboardingImage: AiutaImage?,
    // Onboarding screen
    public val onboardingImages: AiutaOnboadringImages,
    // Selector screen
    public val selectorEmptyImage: AiutaImage,
    // Result screen
    public val feedbackThanksImage: AiutaImage,
)

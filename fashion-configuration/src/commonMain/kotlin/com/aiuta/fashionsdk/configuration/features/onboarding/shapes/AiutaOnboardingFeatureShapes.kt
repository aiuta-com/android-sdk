package com.aiuta.fashionsdk.configuration.features.onboarding.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Abstract class defining shape configurations for the onboarding feature.
 * 
 * This class provides corner radius values and corresponding shapes for
 * different sizes of images used in the onboarding flow.
 */
public abstract class AiutaOnboardingFeatureShapes {
    /**
     * Corner radius for large images in the onboarding flow.
     */
    public abstract val onboardingImageL: Dp

    /**
     * Corner radius for small images in the onboarding flow.
     */
    public abstract val onboardingImageS: Dp

    /**
     * Shape for large images with rounded corners.
     */
    public val onboardingImageLShape: CornerBasedShape by lazy { RoundedCornerShape(onboardingImageL) }

    /**
     * Shape for small images with rounded corners.
     */
    public val onboardingImageSShape: CornerBasedShape by lazy { RoundedCornerShape(onboardingImageS) }

    /**
     * Default implementation of [AiutaOnboardingFeatureShapes].
     * 
     * Provides standard corner radius values for both large and small images.
     */
    public class Default : AiutaOnboardingFeatureShapes() {
        override val onboardingImageL: Dp = 16.dp
        override val onboardingImageS: Dp = 16.dp
    }
}

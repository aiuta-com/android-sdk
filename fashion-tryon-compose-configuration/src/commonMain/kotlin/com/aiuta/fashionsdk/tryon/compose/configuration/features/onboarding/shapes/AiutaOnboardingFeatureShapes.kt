package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public interface AiutaOnboardingFeatureShapes {
    public val onboardingImageL: Dp
    public val onboardingImageS: Dp

    public class Default : AiutaOnboardingFeatureShapes {
        override val onboardingImageL: Dp = 16.dp
        override val onboardingImageS: Dp = 16.dp
    }
}

package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public abstract class AiutaOnboardingFeatureShapes {
    public abstract val onboardingImageL: Dp
    public abstract val onboardingImageS: Dp

    public val onboardingImageLShape: CornerBasedShape by lazy { RoundedCornerShape(onboardingImageL) }
    public val onboardingImageSShape: CornerBasedShape by lazy { RoundedCornerShape(onboardingImageS) }

    public class Default : AiutaOnboardingFeatureShapes() {
        override val onboardingImageL: Dp = 16.dp
        override val onboardingImageS: Dp = 16.dp
    }
}

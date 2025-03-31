package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

internal val AiutaOnboardingFeatureShapes.onboardingImageLShape: CornerBasedShape
    get() = RoundedCornerShape(onboardingImageL)

internal val AiutaOnboardingFeatureShapes.onboardingImageSShape: CornerBasedShape
    get() = RoundedCornerShape(onboardingImageS)

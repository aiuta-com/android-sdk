package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.onboarding.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes

internal val AiutaOnboardingFeatureShapes.onboardingImageLShape: CornerBasedShape
    get() = RoundedCornerShape(onboardingImageL)

internal val AiutaOnboardingFeatureShapes.onboardingImageSShape: CornerBasedShape
    get() = RoundedCornerShape(onboardingImageS)

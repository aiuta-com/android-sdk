package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.onboarding

import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsent

internal data class AiutaConsentUiModel(
    val consent: AiutaConsent,
    val isObtained: Boolean,
)

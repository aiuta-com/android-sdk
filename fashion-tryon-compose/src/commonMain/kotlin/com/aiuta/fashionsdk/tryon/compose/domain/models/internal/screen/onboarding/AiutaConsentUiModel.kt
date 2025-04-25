package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.onboarding

import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsent
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.ConsentEntity

internal data class AiutaConsentUiModel(
    val consent: AiutaConsent,
    val isObtained: Boolean,
)

internal fun AiutaConsentUiModel.toEntity(): ConsentEntity = ConsentEntity(
    id = consent.id,
    isObtained = isObtained,
)

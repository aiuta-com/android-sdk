package com.aiuta.fashionsdk.tryon.compose.domain.internal.utils

import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsent
import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsentType

internal fun AiutaConsent.isRequired() = type != AiutaConsentType.EXPLICIT_OPTIONAL

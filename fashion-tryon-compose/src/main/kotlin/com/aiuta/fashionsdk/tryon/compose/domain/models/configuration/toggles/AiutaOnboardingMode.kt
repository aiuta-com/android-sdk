package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.toggles

public enum class AiutaOnboardingMode {
    STANDARD,

    STANDARD_WITH_CONSENT,

    EXTENDED,
}

internal fun AiutaOnboardingMode.isStandard(): Boolean {
    return this == AiutaOnboardingMode.STANDARD
}

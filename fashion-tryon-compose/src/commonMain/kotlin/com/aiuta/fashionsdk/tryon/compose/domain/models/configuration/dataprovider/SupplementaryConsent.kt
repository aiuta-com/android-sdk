package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider

import com.aiuta.fashionsdk.internal.analytic.model.AiutaSupplementaryConsent

public class SupplementaryConsent(
    public val consentText: String,
    public val isObtained: Boolean,
)

internal fun SupplementaryConsent.toAnalytic(): AiutaSupplementaryConsent {
    return AiutaSupplementaryConsent(
        consentText = consentText,
        isObtained = isObtained,
    )
}

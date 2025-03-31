package com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider

import com.aiuta.fashionsdk.internal.analytic.model.AiutaSupplementaryConsent

public class SupplementaryConsent(
    public val consentText: String,
    public val isObtained: Boolean,
)

public fun SupplementaryConsent.toAnalytic(): AiutaSupplementaryConsent {
    return AiutaSupplementaryConsent(
        consentText = consentText,
        isObtained = isObtained,
    )
}

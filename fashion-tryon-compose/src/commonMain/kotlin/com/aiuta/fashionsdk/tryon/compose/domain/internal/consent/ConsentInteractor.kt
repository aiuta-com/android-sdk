package com.aiuta.fashionsdk.tryon.compose.domain.internal.consent

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.utils.isRequired

internal val Aiuta.consentInteractor: ConsentInteractor
    get() = ConsentInteractor()

internal class ConsentInteractor {

    fun shouldShowConsent(
        consentStandaloneFeature: AiutaConsentStandaloneFeature,
    ): Boolean {
        val hostRequiredConsentIds = consentStandaloneFeature.data.consents
            .filter { it.isRequired() }
            .map { it.id }.toSet()
        val hostObtainedConsentIds = consentStandaloneFeature.dataProvider?.obtainedConsentsIds?.value?.toSet().orEmpty()

        return !hostObtainedConsentIds.containsAll(hostRequiredConsentIds)
    }
}

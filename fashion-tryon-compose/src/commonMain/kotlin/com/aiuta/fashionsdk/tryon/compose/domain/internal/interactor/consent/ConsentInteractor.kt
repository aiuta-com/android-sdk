package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.consent

import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneFeature
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProviderBuiltIn
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProviderCustom
import com.aiuta.fashionsdk.tryon.compose.domain.internal.utils.isRequired
import kotlinx.coroutines.CoroutineScope

internal abstract class ConsentInteractor : AiutaConsentStandaloneFeatureDataProviderCustom {

    fun shouldShowConsent(
        consentStandaloneFeature: AiutaConsentStandaloneFeature,
    ): Boolean {
        val hostRequiredConsentIds = consentStandaloneFeature.data.consents
            .filter { it.isRequired() }
            .map { it.id }.toSet()
        val hostObtainedConsentIds = obtainedConsentsIds.value.toSet()

        return !hostObtainedConsentIds.containsAll(hostRequiredConsentIds)
    }

    companion object {
        fun getInstance(
            scope: CoroutineScope,
            consentStandaloneFeature: AiutaConsentStandaloneFeature?,
        ): ConsentInteractor = when (val dataProvider = consentStandaloneFeature?.dataProvider) {
            null -> EmptyConsentInteractor()

            is AiutaConsentStandaloneFeatureDataProviderBuiltIn -> DatabaseConsentInteractor.getInstance(
                scope = scope,
            )

            is AiutaConsentStandaloneFeatureDataProviderCustom -> HostConsentInteractor(
                customDataProvider = dataProvider,
            )
        }
    }
}

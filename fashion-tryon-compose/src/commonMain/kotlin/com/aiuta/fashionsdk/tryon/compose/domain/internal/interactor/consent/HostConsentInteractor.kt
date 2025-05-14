package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.consent

import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProviderCustom
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProviderLogic

internal class HostConsentInteractor(
    customDataProvider: AiutaConsentStandaloneFeatureDataProviderCustom,
) : ConsentInteractor(),
    AiutaConsentStandaloneFeatureDataProviderLogic by customDataProvider

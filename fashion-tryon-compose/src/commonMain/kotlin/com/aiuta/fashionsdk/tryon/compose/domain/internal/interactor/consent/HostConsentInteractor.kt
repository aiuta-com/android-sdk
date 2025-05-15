package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.consent

import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProviderCustom

internal class HostConsentInteractor(
    customDataProvider: AiutaConsentStandaloneFeatureDataProviderCustom,
) : ConsentInteractor(),
    AiutaConsentStandaloneFeatureDataProviderCustom by customDataProvider

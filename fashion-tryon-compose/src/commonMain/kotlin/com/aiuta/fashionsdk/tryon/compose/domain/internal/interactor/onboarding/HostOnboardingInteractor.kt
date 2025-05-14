package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderCustom
import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderLogic

internal class HostOnboardingInteractor(
    customDataProvider: AiutaOnboardingFeatureDataProviderCustom,
) : OnboardingInteractor,
    AiutaOnboardingFeatureDataProviderLogic by customDataProvider

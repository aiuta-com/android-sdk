package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderCustom

internal class HostOnboardingInteractor(
    customDataProvider: AiutaOnboardingFeatureDataProviderCustom,
) : OnboardingInteractor,
    AiutaOnboardingFeatureDataProviderCustom by customDataProvider

package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderBuiltIn
import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderCustom
import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderLogic
import kotlinx.coroutines.CoroutineScope

internal interface OnboardingInteractor : AiutaOnboardingFeatureDataProviderLogic {
    companion object {
        fun getInstance(
            aiuta: Aiuta,
            scope: CoroutineScope,
            onboardingFeature: AiutaOnboardingFeature?,
        ): OnboardingInteractor = when (val dataProvider = onboardingFeature?.dataProvider) {
            null -> EmptyOnboardingInteractor()

            is AiutaOnboardingFeatureDataProviderBuiltIn -> DatabaseOnboardingInteractor.getInstance(
                scope = scope,
                platformContext = aiuta.platformContext,
            )

            is AiutaOnboardingFeatureDataProviderCustom -> HostOnboardingInteractor(
                customDataProvider = dataProvider,
            )
        }
    }
}

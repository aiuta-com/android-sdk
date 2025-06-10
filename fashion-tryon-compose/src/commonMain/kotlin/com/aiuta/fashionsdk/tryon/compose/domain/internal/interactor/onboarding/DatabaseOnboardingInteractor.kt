package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.OnboardingDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class DatabaseOnboardingInteractor(
    private val scope: CoroutineScope,
    private val onboardingDataSource: OnboardingDataSource,
) : OnboardingInteractor {

    override val isOnboardingCompleted: StateFlow<Boolean> = onboardingDataSource
        .getOnboardingEntitiesFlow()
        .distinctUntilChanged()
        .map { entities -> entities.isNotEmpty() }
        .stateIn(scope, SharingStarted.WhileSubscribed(5000L), false)

    override suspend fun completeOnboarding() {
        onboardingDataSource.insertOnboardingMeta()
    }

    companion object {
        fun getInstance(
            scope: CoroutineScope,
        ): DatabaseOnboardingInteractor = DatabaseOnboardingInteractor(
            scope = scope,
            onboardingDataSource = OnboardingDataSource.getInstance(),
        )
    }
}

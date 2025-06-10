package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.OnboardingDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

internal class DatabaseOnboardingInteractor(
    private val scope: CoroutineScope,
    private val onboardingDataSource: OnboardingDataSource,
) : OnboardingInteractor {

    init {
        observeOnboardingCompletion()
    }

    private val _isOnboardingCompleted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isOnboardingCompleted: StateFlow<Boolean> = _isOnboardingCompleted

    override suspend fun completeOnboarding() {
        onboardingDataSource.insertOnboardingMeta()
    }

    private fun observeOnboardingCompletion() {
        onboardingDataSource
            .count()
            .distinctUntilChanged()
            .onEach { count -> _isOnboardingCompleted.value = count > 0 }
            .map { count -> count > 0 }
            .launchIn(scope)
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

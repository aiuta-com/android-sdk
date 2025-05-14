package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.consent

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.consent.ConsentDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class DatabaseConsentInteractor(
    private val scope: CoroutineScope,
    private val consentDataSource: ConsentDataSource,
) : ConsentInteractor() {
    override val obtainedConsentsIds: StateFlow<List<String>> = consentDataSource
        .obtainedConsentsFlow()
        .distinctUntilChanged()
        .map { entities -> entities.map { it.id } }
        .stateIn(scope, SharingStarted.WhileSubscribed(5000L), emptyList())

    override suspend fun obtainConsent(consentIds: List<String>) {
        consentDataSource.insertObtainedConsent(consentIds)
    }

    companion object {
        fun getInstance(
            scope: CoroutineScope,
            platformContext: AiutaPlatformContext,
        ): DatabaseConsentInteractor = DatabaseConsentInteractor(
            scope = scope,
            consentDataSource = ConsentDataSource.getInstance(platformContext),
        )
    }
}

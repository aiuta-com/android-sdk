package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.consent

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class EmptyConsentInteractor : ConsentInteractor() {
    override val obtainedConsentsIds: StateFlow<List<String>> = MutableStateFlow(emptyList())

    override suspend fun obtainConsent(consentIds: List<String>) {
        error(
            """
                You are trying to use obtain consent method without initializing data provider for it.
                Please use standalone consent feature with initialized data provider.
            """.trimIndent(),
        )
    }
}

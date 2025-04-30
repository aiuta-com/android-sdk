package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.consent.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneFeature
import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsentType
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.onboarding.AiutaConsentUiModel
import kotlin.collections.orEmpty
import kotlin.collections.toSet

@Composable
internal fun rememberConsentController(
    consentStandaloneFeature: AiutaConsentStandaloneFeature,
): ConsentController = remember {
    ConsentController(
        consents = consentStandaloneFeature.toUiModels(),
    )
}

internal class ConsentController(
    consents: List<AiutaConsentUiModel>,
) {
    val consentsList = mutableStateListOf(*consents.toTypedArray())
}

internal fun AiutaConsentStandaloneFeature.toUiModels(): List<AiutaConsentUiModel> {
    val obtainedConsentIds = dataProvider?.obtainedConsentsIds?.value.orEmpty().toSet()
    val allConsents = data.consents
    return allConsents.map { consent ->
        val isHostObtained = obtainedConsentIds.contains(consent.id)
        val isObtainedType = consent.type == AiutaConsentType.IMPLICIT_WITHOUT_CHECKBOX || consent.type == AiutaConsentType.IMPLICIT_WITH_CHECKBOX
        AiutaConsentUiModel(
            consent = consent,
            isObtained = isHostObtained || isObtainedType,
        )
    }
}

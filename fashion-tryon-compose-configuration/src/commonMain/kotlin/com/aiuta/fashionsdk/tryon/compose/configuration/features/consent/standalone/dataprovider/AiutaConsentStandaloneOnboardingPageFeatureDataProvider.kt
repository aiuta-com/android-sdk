package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.consent.SupplementaryConsent
import kotlinx.coroutines.flow.StateFlow

public interface AiutaConsentStandaloneOnboardingPageFeatureDataProvider {
    public val isUserConsentObtainedFlow: StateFlow<Boolean>
    public val obtainUserConsentAction: (List<SupplementaryConsent>) -> Unit
}

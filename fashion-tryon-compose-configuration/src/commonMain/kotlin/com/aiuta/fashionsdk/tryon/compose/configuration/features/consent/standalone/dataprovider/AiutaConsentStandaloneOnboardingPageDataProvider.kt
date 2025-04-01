package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.SupplementaryConsent
import kotlinx.coroutines.flow.StateFlow

public class AiutaConsentStandaloneOnboardingPageDataProvider(
    public val isUserConsentObtainedFlow: StateFlow<Boolean>,
    public val obtainUserConsentAction: (List<SupplementaryConsent>) -> Unit,
)

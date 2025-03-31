package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.consent.standalone.dataprovider

import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.SupplementaryConsent
import kotlinx.coroutines.flow.StateFlow

public class AiutaStandaloneOnboardingPageDataProvider(
    public val isUserConsentObtainedFlow: StateFlow<Boolean>,
    public val obtainUserConsentAction: (List<SupplementaryConsent>) -> Unit,
)

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentStandaloneOnboardingPageFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.onboarding.AiutaConsentUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.consent.controller.ConsentController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.consent.controller.rememberConsentController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.OnboardingState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberOnboardingController(): OnboardingController {
    val consentFeature = provideFeature<AiutaConsentFeature>()
    val onboardingFeature = strictProvideFeature<AiutaOnboardingFeature>()

    val consentController = provideFeature<AiutaConsentStandaloneOnboardingPageFeature>()?.let {
        rememberConsentController(it)
    }

    val onboardingStatesQueue = remember {
        val rawOnboardingQueue = mutableListOf<OnboardingState>()

        // Try on page
        rawOnboardingQueue.add(TryOnPage(onboardingFeature.howItWorksPage))

        // Best result
        onboardingFeature.bestResultsPage?.let { bestResultsPageFeature ->
            rawOnboardingQueue.add(BestResultPage(bestResultsPageFeature))
        }

        // Consent
        if (consentFeature is AiutaConsentStandaloneOnboardingPageFeature) {
            rawOnboardingQueue.add(ConsentPage(consentFeature))
        }

        rawOnboardingQueue
    }

    val pagerState = rememberPagerState(
        pageCount = { onboardingStatesQueue.sumOf { it.pageSize() } },
    )
    val scope = rememberCoroutineScope()

    return remember {
        OnboardingController(
            consents =
            (consentFeature as? AiutaConsentStandaloneOnboardingPageFeature)
                ?.toUiModels()
                .orEmpty(),
            onboardingStatesQueue = onboardingStatesQueue,
            pagerState = pagerState,
            consentController = consentController,
            scope = scope,
        )
    }
}

@Immutable
internal class OnboardingController(
    consents: List<AiutaConsentUiModel>,
    val onboardingStatesQueue: List<OnboardingState>,
    val pagerState: PagerState,
    val consentController: ConsentController?,
    internal val scope: CoroutineScope,
) {
    // Agreement
    @Deprecated("Migrate to consent controller")
    val consentsCheckList = mutableStateListOf(*consents.toTypedArray())

    // General state
    val state: MutableState<OnboardingState> = mutableStateOf(onboardingStatesQueue.first())
}

@Deprecated("Migrate to consent controller")
internal fun AiutaConsentStandaloneOnboardingPageFeature.toUiModels(): List<AiutaConsentUiModel> {
    val obtainedConsentIds = dataProvider?.obtainedConsentsIds?.value.orEmpty().toSet()
    val allConsents = data.consents
    return allConsents.map { consent ->
        AiutaConsentUiModel(
            consent = consent,
            isObtained = obtainedConsentIds.contains(consent.id),
        )
    }
}

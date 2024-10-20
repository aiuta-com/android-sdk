package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.ONBOARDING_EVENT)
public class AiutaAnalyticOnboardingEvent(
    @SerialName("event")
    public val event: AiutaAnalyticOnboardingEventType,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticOnboardingEventType {
    @SerialName("welcomeStartClicked")
    WELCOME_START_CLICKED,

    @SerialName("consentGiven")
    CONSENT_GIVEN,

    @SerialName("onboardingFinished")
    ONBOARDING_FINISHED,
}

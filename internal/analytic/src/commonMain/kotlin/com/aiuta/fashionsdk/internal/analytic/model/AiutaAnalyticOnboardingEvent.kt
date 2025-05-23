package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.ONBOARDING_EVENT)
public class AiutaAnalyticOnboardingEvent(
    @SerialName("event")
    public val event: AiutaAnalyticOnboardingEventType,
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    public override val productId: String?,
    @SerialName("consentsIds")
    public val consentsIds: List<String>? = null,
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

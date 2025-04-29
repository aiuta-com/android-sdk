package com.aiuta.fashionsdk.configuration.features.consent.builtin.strings

public interface AiutaConsentEmbeddedIntoOnboardingFeatureStrings {
    public val consentHtml: String

    public class Default(
        termsOfServiceUrl: String,
    ) : AiutaConsentEmbeddedIntoOnboardingFeatureStrings {
        override val consentHtml: String =
            "Your photos will be processed by <b><a href=\"$termsOfServiceUrl\">Terms of Use</a></b>"
    }
}

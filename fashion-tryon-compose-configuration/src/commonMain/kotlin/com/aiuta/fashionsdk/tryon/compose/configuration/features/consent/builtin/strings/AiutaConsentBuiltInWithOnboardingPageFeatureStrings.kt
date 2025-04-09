package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.strings

public interface AiutaConsentBuiltInWithOnboardingPageFeatureStrings {
    public val consentHtml: String

    public class Default(
        termsOfServiceUrl: String,
    ) : AiutaConsentBuiltInWithOnboardingPageFeatureStrings {
        override val consentHtml: String =
            "Your photos will be processed by <b><a href=\"$termsOfServiceUrl\">Terms of Use</a></b>"
    }
}

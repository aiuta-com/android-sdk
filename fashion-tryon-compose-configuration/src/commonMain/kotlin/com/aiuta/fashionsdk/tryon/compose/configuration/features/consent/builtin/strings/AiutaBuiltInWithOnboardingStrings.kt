package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.strings

public interface AiutaBuiltInWithOnboardingStrings {
    public val consentHtml: String

    public class Default(
        termsOfServiceUrl: String,
    ) : AiutaBuiltInWithOnboardingStrings {
        override val consentHtml: String =
            "Your photos will be processed by <b><a href=\"$termsOfServiceUrl\">Terms of Use</a></b>"
    }
}

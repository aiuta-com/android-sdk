package com.aiuta.fashionsdk.configuration.features.consent.builtin.strings

/**
 * Interface for embedded consent text strings.
 *
 * This interface defines the text strings used in the embedded consent interface,
 * allowing for customization of user-facing text in the consent UI.
 *
 * @property consentHtml HTML-formatted text explaining the consent terms and conditions
 */
public interface AiutaConsentEmbeddedIntoOnboardingFeatureStrings {
    public val consentHtml: String

    /**
     * Default implementation of [AiutaConsentEmbeddedIntoOnboardingFeatureStrings].
     *
     * Provides standard English text strings for the embedded consent interface,
     * with a link to the terms of service.
     *
     * @param termsOfServiceUrl URL to the terms of service document
     */
    public class Default(
        termsOfServiceUrl: String,
    ) : AiutaConsentEmbeddedIntoOnboardingFeatureStrings {
        override val consentHtml: String =
            "Your photos will be processed by <b><a href=\"$termsOfServiceUrl\">Terms of Use</a></b>"
    }
}

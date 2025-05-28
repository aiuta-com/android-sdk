package com.aiuta.fashionsdk.configuration.features.consent.standalone.strings

/**
 * Interface for standalone consent feature text strings.
 *
 * This interface defines the text strings used in the standalone consent interface,
 * allowing for customization of user-facing text in the consent UI.
 *
 * @property consentPageTitle Optional title text displayed at the top of the consent page
 * @property consentTitle Title for the consent section
 * @property consentDescriptionHtml HTML-formatted description explaining the consent terms and conditions
 * @property consentFooterHtml Optional HTML-formatted footer text for additional information
 * @property consentButtonAccept Text for the accept/continue button
 */
public interface AiutaConsentStandaloneFeatureStrings {
    public val consentPageTitle: String?
    public val consentTitle: String
    public val consentDescriptionHtml: String
    public val consentFooterHtml: String?
    public val consentButtonAccept: String

    /**
     * Default implementation of [AiutaConsentStandaloneFeatureStrings].
     *
     * Provides standard English text strings for the standalone consent interface,
     * including links to the terms of service and privacy policy.
     *
     * @param brand Brand name to display in the consent text
     * @param termsOfServiceUrl URL to the terms of service document
     * @param privacyPolicyUrl URL to the privacy policy document
     */
    public class Default(
        brand: String = "Aiuta",
        termsOfServiceUrl: String = "https://aiuta.com/legal/terms-of-service.html",
        privacyPolicyUrl: String = "https://aiuta.com/legal/privacy-policy.html",
    ) : AiutaConsentStandaloneFeatureStrings {
        override val consentPageTitle: String = "<b>Step 3/3</b> - Consent"
        override val consentTitle: String = "Consent"
        override val consentDescriptionHtml: String =
            "In order to try on items digitally, you agree to allow $brand to process your photo." +
                " Your data will be processed according to the $brand <b><a href=\"$privacyPolicyUrl\">Privacy Notice</a></b> " +
                "and <b><a href=\"$termsOfServiceUrl\">Terms of Use.</a></b>"
        override val consentFooterHtml: String? = null
        override val consentButtonAccept: String = "Start"
    }
}

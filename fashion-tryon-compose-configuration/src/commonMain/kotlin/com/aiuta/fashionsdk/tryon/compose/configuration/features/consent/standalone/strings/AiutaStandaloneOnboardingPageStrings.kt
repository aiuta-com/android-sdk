package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings

public interface AiutaStandaloneOnboardingPageStrings {
    public val consentPageTitle: String?
    public val consentTitle: String
    public val consentDescriptionHtml: String
    public val mandatoryConsentHtml: String
    public val optionalConsentsHtml: List<String>
    public val consentFooterHtml: String?

    public class Default(
        brand: String,
        termsOfServiceUrl: String,
        privacyPolicyUrl: String,
        hostOptionalConsentsHtml: List<String>,
    ) : AiutaStandaloneOnboardingPageStrings {
        override val consentPageTitle: String = "<b>Step 3/3</b> - Consent"
        override val consentTitle: String = "Consent"
        override val consentDescriptionHtml: String =
            "In order to try on items digitally, you agree to allow $brand to process your photo." +
                " Your data will be processed according to the $brand <b><a href=\"$privacyPolicyUrl\">Privacy Notice</a></b> " +
                "and <b><a href=\"$termsOfServiceUrl\">Terms of Use.</a></b>"
        override val mandatoryConsentHtml: String = "I agree to allow $brand to process my photo"
        override val optionalConsentsHtml: List<String> = hostOptionalConsentsHtml
        override val consentFooterHtml: String? = null
    }
}

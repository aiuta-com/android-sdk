package com.aiuta.fashionsdk.configuration.features.consent.standalone.strings

public interface AiutaConsentStandaloneFeatureStrings {
    public val consentPageTitle: String?
    public val consentTitle: String
    public val consentDescriptionHtml: String
    public val consentFooterHtml: String?
    public val consentButtonAccept: String

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

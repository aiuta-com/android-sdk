package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaConsentStandaloneOnboardingPageFeatureStrings {
    public val consentPageTitle: String?
    public val consentTitle: String
    public val consentDescriptionHtml: String
    public val consentFooterHtml: String?

    public class Default(
        brand: String = "Aiuta",
        termsOfServiceUrl: String = "https://aiuta.com/legal/terms-of-service.html",
        privacyPolicyUrl: String = "https://aiuta.com/legal/privacy-policy.html",
    ) : AiutaConsentStandaloneOnboardingPageFeatureStrings {
        override val consentPageTitle: String = "<b>Step 3/3</b> - Consent"
        override val consentTitle: String = "Consent"
        override val consentDescriptionHtml: String =
            "In order to try on items digitally, you agree to allow $brand to process your photo." +
                " Your data will be processed according to the $brand <b><a href=\"$privacyPolicyUrl\">Privacy Notice</a></b> " +
                "and <b><a href=\"$termsOfServiceUrl\">Terms of Use.</a></b>"
        override val consentFooterHtml: String? = null
    }
}

internal val AiutaConsentStandaloneOnboardingPageFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "consentPageTitle",
            string = consentPageTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "consentTitle",
            string = consentTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "consentDescriptionHtml",
            string = consentDescriptionHtml,
        ),
        AiutaStringValidationContainer(
            propertyName = "consentFooterHtml",
            string = consentFooterHtml,
        ),
    )

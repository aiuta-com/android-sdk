package com.aiuta.fashionsdk.tryon.compose.configuration.language

/**
 * One of the languages supported by AiutaTryOn flow
 */
public interface AiutaTryOnLanguage

public class EnglishLanguage(
    public val brand: String,
    public val termsOfServiceUrl: String,
    public val privacyPolicyUrl: String,
    public val onboardingPageConsentSupplementaryPoints: List<String>,
) : AiutaTryOnLanguage {
    public companion object {
        // Code in ISO-639
        public const val CODE: String = "en"
    }
}

public class TurkishLanguage(
    public val brand: String,
    public val termsOfServiceUrl: String,
    public val privacyPolicyUrl: String,
    public val onboardingPageConsentSupplementaryPoints: List<String>,
) : AiutaTryOnLanguage {
    public companion object {
        // Code in ISO-639
        public const val CODE: String = "tr"
    }
}

public class RussianLanguage(
    public val brand: String,
    public val termsOfServiceUrl: String,
    public val privacyPolicyUrl: String,
    public val onboardingPageConsentSupplementaryPoints: List<String>,
) : AiutaTryOnLanguage {
    public companion object {
        // Code in ISO-639
        public const val CODE: String = "ru"
    }
}

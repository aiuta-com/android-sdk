package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

/**
 * One of the languages supported by AiutaTryOn flow
 */
public sealed interface AiutaTryOnLanguage

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

public class CustomLanguage(
    // App bar
    override val appBarHistory: String,
    override val appBarSelect: String,
    // Pre Onboarding
    override val preOnboardingTitle: String,
    override val preOnboardingSubtitle: String,
    override val preOnboardingButton: String,
    // Onboarding
    override val onboardingButtonNext: String,
    override val onboardingButtonStart: String,
    override val onboardingPageTryonTopic: String,
    override val onboardingPageTryonSubtopic: String,
    override val onboardingPageTryonConsent: String,
    override val onboardingPageBestResultTopic: String,
    override val onboardingPageBestResultSubtopic: String,
    override val onboardingPageConsentTopic: String,
    override val onboardingPageConsentBody: String,
    override val onboardingPageConsentSupplementaryPoints: List<String>,
    override val onboardingPageConsentAgreePoint: String,
    override val onboardingAppbarTryonPage: String,
    override val onboardingAppbarBestResultPage: String,
    override val onboardingAppbarConsentPage: String,
    override val onboardingPageConsentFooter: String?,
    // Image selector
    override val imageSelectorUploadTitle: String,
    override val imageSelectorUploadSubtitle: String,
    override val imageSelectorUploadButton: String,
    override val imageSelectorSelectModelButton: String,
    override val imageSelectorOr: String,
    override val imageSelectorChangeButton: String,
    override val imageSelectorPoweredByAiuta: String,
    override val imageSelectorProtectionPoint: String,
    override val imageSelectorUploadingImage: String,
    override val imageSelectorScanningBody: String,
    override val imageSelectorGeneratingOutfit: String,
    override val modelSelectorCategories: List<TranslationWord>,
    override val modelSelectorErrorEmptyModelsList: String,
    // History
    override val historySelectorEnableButtonSelectAll: String,
    override val historySelectorEnableButtonUnselectAll: String,
    override val historyEmptyDescription: String,
    // Generation Result
    override val generationResultMoreTitle: String,
    override val generationResultMoreSubtitle: String,
    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String,
    override val pickerSheetChooseLibrary: String,
    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String,
    override val generatedOperationsSheetUploadNewButton: String,
    override val generatedOperationsSheetUploadNewButtonWithModels: String,
    // Feedback sheet
    override val feedbackSheetSkip: String,
    override val feedbackSheetSend: String,
    override val feedbackSheetSendFeedback: String,
    public val feedbackSheetTitle: String?,
    public val feedbackSheetOptions: List<String>?,
    public val feedbackSheetExtraOption: String?,
    public val feedbackSheetExtraOptionTitle: String?,
    public val feedbackSheetGratitude: String?,
    // Disclaimer
    override val fitDisclaimerTitle: String,
    override val fitDisclaimerBody: String,
    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String,
    override val dialogCameraPermissionDescription: String,
    override val dialogCameraPermissionConfirmButton: String,
    // Invalid image
    override val dialogInvalidImageDescription: String,
    // General
    override val addToWish: String,
    override val addToCart: String,
    override val cancel: String,
    override val close: String,
    override val modelSelect: String,
    override val tryOn: String,
    override val tryAgain: String,
    override val virtualTryOn: String,
    override val share: String,
    override val defaultErrorMessage: String,
) : AiutaTryOnLanguage, InternalAiutaTryOnLanguage

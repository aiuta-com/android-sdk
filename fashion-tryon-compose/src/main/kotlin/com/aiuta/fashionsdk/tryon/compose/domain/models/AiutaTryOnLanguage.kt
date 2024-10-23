package com.aiuta.fashionsdk.tryon.compose.domain.models

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

/**
 * One of the languages supported by AiutaTryOn flow
 */
public sealed interface AiutaTryOnLanguage

public class EnglishLanguage(
    public val brand: String,
    public val termsOfServiceUrl: String,
    public val privacyPolicyUrl: String,
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
    override val onboardingPageBestResultTopic: String,
    override val onboardingPageBestResultSubtopic: String,
    override val onboardingPageConsentTopic: String,
    override val onboardingPageConsentBody: String,
    override val onboardingPageConsentAgreePoint: String,
    override val onboardingAppbarTryonPage: String,
    override val onboardingAppbarBestResultPage: String,
    override val onboardingAppbarConsentPage: String,
    // Image selector
    override val imageSelectorUploadButton: String,
    override val imageSelectorChangeButton: String,
    override val imageSelectorPoweredByAiuta: String,
    override val imageSelectorProtectionPoint: String,
    override val imageSelectorGeneratingOutfit: String,
    // History
    override val historySelectorDisabledButton: String,
    override val historySelectorEnableButtonSelectAll: String,
    override val historySelectorEnableButtonUnselectAll: String,
    override val historySelectorEnableButtonCancel: String,
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
    // Feedback sheet
    override val feedbackSheetSkip: String,
    override val feedbackSheetSend: String,
    override val feedbackSheetSendFeedback: String,
    public val feedbackSheetTitle: String? = null,
    public val feedbackSheetOptions: List<String>? = null,
    public val feedbackSheetExtraOption: String? = null,
    public val feedbackSheetExtraOptionTitle: String? = null,
    // Disclaimer
    override val fitDisclaimerTitle: String,
    override val fitDisclaimerBody: String,
    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String,
    override val dialogCameraPermissionDescription: String,
    override val dialogCameraPermissionConfirmButton: String,
    // General
    override val addToWish: String,
    override val addToCart: String,
    override val cancel: String,
    override val close: String,
    override val tryOn: String,
    override val tryAgain: String,
    override val virtualTryOn: String,
    override val share: String,
    override val defaultErrorMessage: String,
) : AiutaTryOnLanguage, InternalAiutaTryOnLanguage {
    init {
        // App bar
        appBarHistory.atMost(22)
        appBarSelect.atMost(11)
        // Pre Onboarding
        preOnboardingTitle.atMost(16)
        preOnboardingSubtitle.atMost(70)
        preOnboardingButton.atMost(20)
        // Onboarding
        onboardingButtonNext.atMost(20)
        onboardingButtonStart.atMost(20)
        onboardingPageTryonTopic.atMost(26)
        onboardingPageTryonSubtopic.atMost(64)
        onboardingPageBestResultTopic.atMost(26)
        onboardingPageBestResultSubtopic.atMost(64)
        onboardingPageConsentTopic.atMost(30)
        onboardingPageConsentBody.atMost(320)
        onboardingPageConsentAgreePoint.atMost(64)
        onboardingAppbarTryonPage.atMost(30)
        onboardingAppbarBestResultPage.atMost(30)
        onboardingAppbarConsentPage.atMost(30)
        // Image selector
        imageSelectorUploadButton.atMost(20)
        imageSelectorChangeButton.atMost(24)
        imageSelectorPoweredByAiuta.atMost(34)
        imageSelectorProtectionPoint.atMost(60)
        imageSelectorGeneratingOutfit.atMost(22)
        // History
        historySelectorDisabledButton.atMost(11)
        historySelectorEnableButtonSelectAll.atMost(16)
        historySelectorEnableButtonUnselectAll.atMost(16)
        historySelectorEnableButtonCancel.atMost(10)
        historyEmptyDescription.atMost(80)
        // Generation Result
        generationResultMoreTitle.atMost(30)
        generationResultMoreSubtitle.atMost(30)
        // Bottom sheets
        // Picker sheet
        pickerSheetTakePhoto.atMost(26)
        pickerSheetChooseLibrary.atMost(26)
        // Generated operations sheet
        generatedOperationsSheetPreviously.atMost(30)
        generatedOperationsSheetUploadNewButton.atMost(30)
        // Feedback sheet
        feedbackSheetSkip.atMost(30)
        feedbackSheetSend.atMost(20)
        feedbackSheetSendFeedback.atMost(20)
        feedbackSheetTitle?.atMost(44)
        feedbackSheetOptions?.forEach { option -> option.atMost(30) }
        feedbackSheetExtraOption?.atMost(30)
        feedbackSheetExtraOptionTitle?.atMost(44)
        // Disclaimer
        fitDisclaimerTitle.atMost(64)
        fitDisclaimerTitle.atMost(180)
        // Dialog
        // Camera permission
        dialogCameraPermissionTitle.atMost(20)
        dialogCameraPermissionDescription.atMost(100)
        dialogCameraPermissionConfirmButton.atMost(20)
        // General
        addToWish.atMost(16)
        addToCart.atMost(20)
        cancel.atMost(10)
        close.atMost(10)
        tryOn.atMost(20)
        tryAgain.atMost(13)
        virtualTryOn.atMost(22)
        share.atMost(20)
        defaultErrorMessage.atMost(44)
    }

    private fun String.atMost(maxNumber: Int) {
        check(this.length <= maxNumber) {
            "String \"$this\" contains more than max number of chars - ${this.length} > $maxNumber"
        }
    }
}

package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal object TurkishAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "Geçmiş"
    override val appBarSelect: String = "Seç"

    // Pre Onboarding
    override val preOnboardingTitle: String = "TODO"
    override val preOnboardingSubtitle: String = "TODO"
    override val preOnboardingButton: String = "TODO"

    // Onboarding
    override val onboardingButtonNext: String = "Başla"
    override val onboardingButtonStart: String = "Devam Et"

    override val onboardingPageTryonTopic: String = "Satın almadan önce dene"
    override val onboardingPageTryonSubtopic: String = "Fotoğrafını yükle ve üzerinde nasıl göründüğüne bak"
    override val onboardingPageBestResultTopic: String = "En iyi sonuçlar için"
    override val onboardingPageBestResultSubtopic: String = "İyi ışıklandırılmış, düz bir arka planı olan, dik durduğunuz bir fotoğraf kullanın"
    override val onboardingPageConsentTopic: String = "TODO"
    override val onboardingPageConsentBody: String = "TODO"
    override val onboardingPageConsentAgreePoint: String = "TODO"
    override val onboardingAppbarTryonPage: String = "TODO"
    override val onboardingAppbarBestResultPage: String = "TODO"
    override val onboardingAppbarConsentPage: String = "TODO"

    // Image selector
    override val imageSelectorUploadButton: String = "Fotoğrafını yükle"
    override val imageSelectorChangeButton: String = "Fotoğrafını değiştir"
    override val imageSelectorPoweredByAiuta: String = "Tarafından desteklenmektedir Aiuta"
    override val imageSelectorProtectionPoint: String = "TODO"
    override val imageSelectorGeneratingOutfit: String = "Ürün uygulanıyor"

    // History
    override val historySelectorDisabledButton: String = "Seç"
    override val historySelectorEnableButtonSelectAll: String = "Tümünü seçin"
    override val historySelectorEnableButtonUnselectAll: String = "Tümünü Kaldır"
    override val historySelectorEnableButtonCancel: String = "İptal"
    override val historyEmptyDescription: String = "İlk ürün denemenizden sonra deneme geçmişiniz burada saklanacaktır"

    // Generation Result
    override val generationResultMoreTitle: String = "TODO"
    override val generationResultMoreSubtitle: String = "Denemeniz için daha fazlası"

    // Picker sheet
    override val pickerSheetTakePhoto: String = "Fotoğraf çek"
    override val pickerSheetChooseLibrary: String = "Galeriden seç"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Önceden yüklenen fotoğraflar"
    override val generatedOperationsSheetUploadNewButton: String = "+ Yeni fotoğraf yükle"

    // Feedback sheet
    override val feedbackSheetSkip: String = "Atla"
    override val feedbackSheetSend: String = "Göndermek"
    override val feedbackSheetSendFeedback: String = "Geri bildirimi gönder"

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Kamera izni"
    override val dialogCameraPermissionDescription: String = "Lütfen uygulama ayarlarından kameraya erişime izin verin."
    override val dialogCameraPermissionConfirmButton: String = "Ayarlar"

    // General
    override val addToWish: String = "Listelerime ekle"
    override val addToCart: String = "Sepete ekle"
    override val cancel: String = "Vazgeç"
    override val close: String = "TODO"
    override val tryOn: String = "Üzerinde Dene"
    override val virtualTryOn: String = "TODO"
    override val share: String = "Paylaş"
    override val defaultErrorMessage: String = "Bir şeyler yanlış gitti. Lütfen daha sonra tekrar deneyin"
}

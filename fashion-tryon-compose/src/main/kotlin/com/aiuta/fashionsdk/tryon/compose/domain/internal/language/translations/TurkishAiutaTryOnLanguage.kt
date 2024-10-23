package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class TurkishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "Geçmiş"
    override val appBarSelect: String = "Seç"

    // Pre Onboarding
    override val preOnboardingTitle: String = "Üzerinde Dene"
    override val preOnboardingSubtitle: String =
        "Sanal Deneme'ye hoş geldiniz.\nÜrünü fotoğrafınızın üzerinde deneyin"
    override val preOnboardingButton: String = "Başlayalım"

    // Onboarding
    override val onboardingButtonNext: String = "Başla"
    override val onboardingButtonStart: String = "Devam Et"

    override val onboardingPageTryonTopic: String = "Satın almadan önce dene"
    override val onboardingPageTryonSubtopic: String = "Fotoğrafını yükle ve üzerinde nasıl göründüğüne bak"
    override val onboardingPageBestResultTopic: String = "En iyi sonuçlar için"
    override val onboardingPageBestResultSubtopic: String = "İyi ışıklandırılmış, düz bir arka planı olan, dik durduğunuz bir fotoğraf kullanın"
    override val onboardingPageConsentTopic: String = "Onay"
    override val onboardingPageConsentBody: String =
        "Ürünleri dijital olarak denemek için, $brand fotoğrafınızı işlemesine izin verirsiniz." +
            " Verileriniz $brand <b><a href=\"$privacyPolicyUrl\">Gizlilik Bildirimi</a></b> " +
            "ve <b><a href=\"$termsOfServiceUrl\">Kullanım Koşulları</a></b> doğrultusunda işlenecektir."
    override val onboardingPageConsentAgreePoint: String =
        "$brand'ın fotoğrafımı işlemesine izin veriyorum"
    override val onboardingAppbarTryonPage: String = "<b>Adım 1/3</b> - Nasıl çalışır"
    override val onboardingAppbarBestResultPage: String = "<b>Adım 2/3</b> - En iyi sonuçlar"
    override val onboardingAppbarConsentPage: String = "<b>Adım 3/3</b> - Onay"

    // Image selector
    override val imageSelectorUploadButton: String = "Fotoğrafını yükle"
    override val imageSelectorChangeButton: String = "Fotoğrafını değiştir"
    override val imageSelectorPoweredByAiuta: String = "Tarafından desteklenmektedir Aiuta"
    override val imageSelectorProtectionPoint: String = "Fotoğraflarınız koruma altındadır ve yalnızca size görünür"
    override val imageSelectorGeneratingOutfit: String = "Ürün uygulanıyor"

    // History
    override val historySelectorDisabledButton: String = "Seç"
    override val historySelectorEnableButtonSelectAll: String = "Tümünü seçin"
    override val historySelectorEnableButtonUnselectAll: String = "Tümünü Kaldır"
    override val historySelectorEnableButtonCancel: String = "İptal"
    override val historyEmptyDescription: String = "İlk ürün denemenizden sonra deneme geçmişiniz burada saklanacaktır"

    // Generation Result
    override val generationResultMoreTitle: String = "Hoşunuza gidebilecek ürünler"
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
    override val close: String = "Kapat"
    override val tryOn: String = "Üzerinde Dene"
    override val tryAgain: String = "Tekrar dene"
    override val virtualTryOn: String = "Sanal Deneme"
    override val share: String = "Paylaş"
    override val defaultErrorMessage: String = "Bir şeyler yanlış gitti. Lütfen daha sonra tekrar deneyin"
}

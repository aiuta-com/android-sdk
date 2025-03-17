package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.TranslationWord

internal class TurkishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
    override val onboardingPageConsentSupplementaryPoints: List<String>,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "Geçmiş"
    override val appBarSelect: String = "Seç"
    override val appBarModelSelect: String = "Modelinizi seçin"

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
    override val onboardingPageTryonConsent: String = "Fotoğraflarınız <b><a href=\"$termsOfServiceUrl\">Kullanım Koşullarına</a></b> göre işlenecektir"
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
    override val onboardingPageConsentFooter: String? = null

    // Image selector
    override val imageSelectorUploadTitle: String = "Fotoğrafınızı yükleyin"
    override val imageSelectorUploadSubtitle: String = "Dik durduğunuz ve net göründüğünüz bir fotoğraf seçin"
    override val imageSelectorUploadButton: String = "Fotoğraf yükle"
    override val imageSelectorSelectModelButton: String = "Modelinizi seçin"
    override val imageSelectorOr: String = "Veya"
    override val imageSelectorChangeButton: String = "Fotoğrafını değiştir"
    override val imageSelectorPoweredByAiuta: String = "Tarafından desteklenmektedir Aiuta"
    override val imageSelectorProtectionPoint: String = "Fotoğraflarınız koruma altındadır ve yalnızca size görünür"

    override val imageSelectorUploadingImage: String = "Resim yükleme"
    override val imageSelectorScanningBody: String = "Vücudunu taramak"
    override val imageSelectorGeneratingOutfit: String = "Ürün uygulanıyor"

    override val modelSelectorCategories: List<TranslationWord> =
        listOf(
            TranslationWord(id = "woman", translation = "Kadın"),
            TranslationWord(id = "man", translation = "Kadın"),
        )
    override val modelSelectorErrorEmptyModelsList: String = "Model listesi boş"

    // History
    override val historySelectorEnableButtonSelectAll: String = "Tümünü seçin"
    override val historySelectorEnableButtonUnselectAll: String = "Tümünü Kaldır"
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
    override val generatedOperationsSheetUploadNewButtonWithModels: String = "+ Yeni fotoğraf yükle"

    // Feedback sheet
    override val feedbackSheetSkip: String = "Atla"
    override val feedbackSheetSend: String = "Göndermek"
    override val feedbackSheetSendFeedback: String = "Geri bildirimi gönder"

    // Disclaimer
    override val fitDisclaimerTitle: String = "Sonuçlar gerçek hayattaki beden uyumu ile farklılık gösterebilir"
    override val fitDisclaimerBody: String = "Üzerinde Gör, ürünün üzerinizde nasıl gözükeceğini gösteren bir görselleştirme aracıdır. Ancak beden ölçüleriniz dikkate alınmadığı için gerçek hayattaki beden uyumu farklılık gösterebilir."

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Kamera izni"
    override val dialogCameraPermissionDescription: String = "Lütfen uygulama ayarlarından kameraya erişime izin verin."
    override val dialogCameraPermissionConfirmButton: String = "Ayarlar"

    // Invalid image
    override val dialogInvalidImageDescription: String = "Bu fotoğrafta kimseyi tespit edemedik"

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

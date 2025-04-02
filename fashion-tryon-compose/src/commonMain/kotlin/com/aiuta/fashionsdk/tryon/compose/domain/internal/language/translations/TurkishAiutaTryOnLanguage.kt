package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.configuration.language.TranslationWord
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class TurkishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "Geçmiş"
    override val appBarSelect: String = "Seç"

    // Image selector
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
    override val modelSelect: String = "Modelinizi seçin"
    override val tryOn: String = "Üzerinde Dene"
    override val tryAgain: String = "Tekrar dene"
    override val virtualTryOn: String = "Sanal Deneme"
    override val share: String = "Paylaş"
    override val defaultErrorMessage: String = "Bir şeyler yanlış gitti. Lütfen daha sonra tekrar deneyin"
}

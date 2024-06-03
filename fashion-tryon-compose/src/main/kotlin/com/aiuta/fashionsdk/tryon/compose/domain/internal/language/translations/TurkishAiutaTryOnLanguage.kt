package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.Plurals

internal object TurkishAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "Geçmiş"
    override val appBarSelect: String = "Seç"

    // Onboarding
    override val onboardingButtonNext: String = "Başla"
    override val onboardingButtonStart: String = "Devam Et"

    override val onboardingPageTryonTopic: String = "Satın almadan önce dene"
    override val onboardingPageTryonSubtopic: String = "Fotoğrafını yükle ve üzerinde nasıl göründüğüne bak"
    override val onboardingPageBestResultTopic: String = "En iyi sonuçlar için"
    override val onboardingPageBestResultSubtopic: String = "İyi ışıklandırılmış, düz bir arka planı olan, dik durduğunuz bir fotoğraf kullanın"

    // Image selector
    override val imageSelectorUploadButton: String = "Fotoğrafını yükle"
    override val imageSelectorChangeButton: String = "Fotoğrafını değiştir"
    override val imageSelectorPoweredByAiuta1: String = "Tarafından desteklenmektedir "
    override val imageSelectorPoweredByAiuta2: String = "Aiuta"
    override val imageSelectorGeneratingOutfit: String = "Ürün uygulanıyor"
    override val imageSelectorPhotos: Plurals =
        Plurals(
            one = "%d fotoğraf",
            other = "%d fotoğraflar",
        )

    // History
    override val historySelectorDisabledButton: String = "Seç"
    override val historySelectorEnableButtonSelectAll: String = "Tümünü seçin"
    override val historySelectorEnableButtonUnselectAll: String = "Tümünü Kaldır"
    override val historySelectorEnableButtonCancel: String = "İptal"
    override val historyEmptyDescription: String = "İlk ürün denemenizden sonra deneme geçmişiniz burada saklanacaktır"

    // Generation Result
    override val generationResultMoreTitle: String = "Denemeniz için daha fazlası"
    override val generationResultSwipeUp: String = "Daha fazlası için yukarı kaydır"

    // Picker sheet
    override val pickerSheetTakePhoto: String = "Fotoğraf çek"
    override val pickerSheetChooseLibrary: String = "Galeriden seç"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Önceden yüklenen fotoğraflar"
    override val generatedOperationsSheetUploadNewButton: String = "+ Yeni fotoğraf yükle"

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Kamera izni"
    override val dialogCameraPermissionDescription: String = "Lütfen uygulama ayarlarından kameraya erişime izin verin."
    override val dialogCameraPermissionConfirmButton: String = "Ayarlar"
    override val dialogCameraPermissionCancelButton: String = "İptal"

    // General
    override val addToWish: String = "Listelerime ekle"
    override val addToCart: String = "Sepete ekle"
    override val aiutaUrl: String = "https://aiuta.com/"
    override val moreDetails: String = "Daha fazla detay"
    override val tryOn: String = "Üzerinde Dene"
    override val share: String = "Paylaş"
    override val defaultErrorMessage: String = "Bir şeyler yanlış gitti. Lütfen daha sonra tekrar deneyin"
}

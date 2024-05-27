package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.Plurals

internal object TurkishAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "Geçmiş"
    override val appBarSelect: String = "Seç"

    // Onboarding
    override val onboardingButtonNext: String = "Sonraki"
    override val onboardingButtonStart: String = "Başla"
    override val onboardingPageTryonTopic: String = "Satın almadan önce dene"
    override val onboardingPageTryonSubtopic: String = "Sadece fotoğrafını yükle ve üzerinde nasıl göründüğüne bak"
    override val onboardingPageBestResultTopic: String = "En iyi sonuçlar için"
    override val onboardingPageBestResultSubtopic: String = "İyi ışıklandırılmış, düz bir arka planı olan, dik durduğunuz bir fotoğraf kullanın"

    // Image selector
    override val imageSelectorUploadButton: String = "Fotoğrafını yükle"
    override val imageSelectorChangeButton: String = "Fotoğrafını değiştir"
    override val imageSelectorPoweredByAiuta1: String = "Tarafından desteklenmektedir "
    override val imageSelectorPoweredByAiuta2: String = "Aiuta"
    override val imageSelectorGeneratingOutfit: String = "Kıyafet oluşturuluyor"
    override val imageSelectorPhotos: Plurals =
        Plurals(
            one = "%d fotoğraf",
            other = "%d fotoğraflar",
        )

    // History
    override val historySelectorDisabledText: String = "Görüntüleri paylaşmak veya silmek için seçebilirsiniz"
    override val historySelectorDisabledButton: String = "Seç"
    override val historySelectorEnableButtonSelectAll: String = "Hepsini seç"
    override val historySelectorEnableButtonUnselectAll: String = "Hepsini kaldır"
    override val historySelectorEnableButtonCancel: String = "İptal"
    override val historyEmptyDescription: String = "İlk öğeyi denedikten sonra deneme geçmişiniz burada saklanacaktır"

    // Generation Result
    override val generationResultMoreTitle: String = "Denemeniz için daha fazlası"
    override val generationResultSwipeUp: String = "Daha fazlası için yukarı kaydır"

    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String = "Fotoğraf çek"
    override val pickerSheetChooseLibrary: String = "Fotoğraf ekle"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Önceden yüklenen fotoğraflar"
    override val generatedOperationsSheetUploadNewButton: String = "+ Yeni fotoğraf yükle"

    // General
    override val addToWish: String = "Listelerine ekle"
    override val addToCart: String = "Sepete ekle"
    override val moreDetails: String = "Daha fazla detay"
    override val aiutaUrl: String = "https://aiuta.com/"
    override val tryOn: String = "Dene"
    override val share: String = "Paylaş"
}

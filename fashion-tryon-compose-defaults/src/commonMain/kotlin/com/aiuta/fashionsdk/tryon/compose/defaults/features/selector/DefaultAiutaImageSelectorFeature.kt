package com.aiuta.fashionsdk.tryon.compose.defaults.features.selector

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.camera
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.strings.AiutaImageSelectorCameraStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.photoGallery
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.strings.AiutaImageSelectorPhotoGalleryStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.buttons.AiutaImageSelectorUploadsHistoryButtons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings.AiutaImageSelectorUploadsHistoryStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.uploadsHistory
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.imageSelector
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.predefinedModels
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.strings.AiutaImageSelectorPredefinedModelStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings.AiutaImageSelectorFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.camera.DefaultAiutaImageSelectorCameraIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.gallery.DefaultAiutaImageSelectorPhotoGalleryIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.model.DefaultAiutaImageSelectorPredefinedModelIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.selector.DefaultAiutaImageSelectorFeatureImages

public fun AiutaTryOnFeatures.Builder.defaultImageSelector() {
    imageSelector {
        camera {
            icons = DefaultAiutaImageSelectorCameraIcons()
            strings = AiutaImageSelectorCameraStrings.Default()
        }
        photoGallery {
            icons = DefaultAiutaImageSelectorPhotoGalleryIcons()
            strings = AiutaImageSelectorPhotoGalleryStrings.Default()
        }
        predefinedModels {
            icons = DefaultAiutaImageSelectorPredefinedModelIcons()
            strings = AiutaImageSelectorPredefinedModelStrings.Default()
        }
        uploadsHistory {
            strings = AiutaImageSelectorUploadsHistoryStrings.Default(isPredefinedModelAvailable = true)
            buttons = AiutaImageSelectorUploadsHistoryButtons.Default()
        }
        images = DefaultAiutaImageSelectorFeatureImages()
        strings = AiutaImageSelectorFeatureStrings.Default()
    }
}

package com.aiuta.fashionsdk.tryon.compose.defaults.features.selector

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.selector.camera.camera
import com.aiuta.fashionsdk.configuration.features.features.selector.camera.strings.AiutaImageSelectorCameraFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.selector.gallery.photoGallery
import com.aiuta.fashionsdk.configuration.features.features.selector.gallery.strings.AiutaImageSelectorPhotoGalleryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.selector.history.strings.AiutaImageSelectorUploadsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.selector.history.styles.AiutaImageSelectorUploadsHistoryFeatureStyles
import com.aiuta.fashionsdk.configuration.features.features.selector.history.uploadsHistory
import com.aiuta.fashionsdk.configuration.features.features.selector.imageSelector
import com.aiuta.fashionsdk.configuration.features.features.selector.model.predefinedModels
import com.aiuta.fashionsdk.configuration.features.features.selector.model.strings.AiutaImageSelectorPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.selector.strings.AiutaImageSelectorFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.camera.DefaultAiutaImageSelectorCameraFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.gallery.DefaultAiutaImageSelectorPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.model.DefaultAiutaImageSelectorPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.selector.DefaultAiutaImageSelectorFeatureImages

public fun AiutaTryOnConfigurationFeatures.Builder.defaultImageSelector() {
    imageSelector {
        camera {
            icons = DefaultAiutaImageSelectorCameraFeatureIcons()
            strings = AiutaImageSelectorCameraFeatureStrings.Default()
        }
        photoGallery {
            icons = DefaultAiutaImageSelectorPhotoGalleryFeatureIcons()
            strings = AiutaImageSelectorPhotoGalleryFeatureStrings.Default()
        }
        predefinedModels {
            icons = DefaultAiutaImageSelectorPredefinedModelFeatureIcons()
            strings = AiutaImageSelectorPredefinedModelFeatureStrings.Default()
        }
        uploadsHistory {
            strings = AiutaImageSelectorUploadsHistoryFeatureStrings.Default(isPredefinedModelAvailable = true)
            styles = AiutaImageSelectorUploadsHistoryFeatureStyles.Default()
        }
        images = DefaultAiutaImageSelectorFeatureImages()
        strings = AiutaImageSelectorFeatureStrings.Default()
    }
}

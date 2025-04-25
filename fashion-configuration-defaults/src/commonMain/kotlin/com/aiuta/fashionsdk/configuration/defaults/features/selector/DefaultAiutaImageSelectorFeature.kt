package com.aiuta.fashionsdk.configuration.defaults.features.selector

import com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.camera.DefaultAiutaImageSelectorCameraFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.gallery.DefaultAiutaImageSelectorPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.model.DefaultAiutaImageSelectorPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.images.features.selector.DefaultAiutaImageSelectorFeatureImages
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.selector.camera.camera
import com.aiuta.fashionsdk.configuration.features.selector.camera.strings.AiutaImageSelectorCameraFeatureStrings
import com.aiuta.fashionsdk.configuration.features.selector.gallery.photoGallery
import com.aiuta.fashionsdk.configuration.features.selector.gallery.strings.AiutaImageSelectorPhotoGalleryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.selector.history.strings.AiutaImageSelectorUploadsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.selector.history.styles.AiutaImageSelectorUploadsHistoryFeatureStyles
import com.aiuta.fashionsdk.configuration.features.selector.history.uploadsHistory
import com.aiuta.fashionsdk.configuration.features.selector.imageSelector
import com.aiuta.fashionsdk.configuration.features.selector.model.predefinedModels
import com.aiuta.fashionsdk.configuration.features.selector.model.strings.AiutaImageSelectorPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.features.selector.strings.AiutaImageSelectorFeatureStrings

public fun AiutaFeatures.Builder.defaultImageSelector() {
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

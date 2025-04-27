package com.aiuta.fashionsdk.configuration.defaults.features.selector

import com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.camera.DefaultAiutaImagePickerCameraFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.gallery.DefaultAiutaImagePickerPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.model.DefaultAiutaImageSelectorPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.images.features.selector.DefaultAiutaImagePickerFeatureImages
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.picker.camera.camera
import com.aiuta.fashionsdk.configuration.features.picker.camera.strings.AiutaImagePickerCameraFeatureStrings
import com.aiuta.fashionsdk.configuration.features.picker.gallery.photoGallery
import com.aiuta.fashionsdk.configuration.features.picker.gallery.strings.AiutaImagePickerPhotoGalleryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.picker.history.strings.AiutaImagePickerUploadsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.picker.history.styles.AiutaImagePickerUploadsHistoryFeatureStyles
import com.aiuta.fashionsdk.configuration.features.picker.history.uploadsHistory
import com.aiuta.fashionsdk.configuration.features.picker.imagePicker
import com.aiuta.fashionsdk.configuration.features.picker.model.predefinedModels
import com.aiuta.fashionsdk.configuration.features.picker.model.strings.AiutaImageSelectorPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.features.picker.strings.AiutaImagePickerFeatureStrings

public fun AiutaFeatures.Builder.defaultImagePicker(): AiutaFeatures.Builder = imagePicker {
    camera {
        icons = DefaultAiutaImagePickerCameraFeatureIcons()
        strings = AiutaImagePickerCameraFeatureStrings.Default()
    }
    photoGallery {
        icons = DefaultAiutaImagePickerPhotoGalleryFeatureIcons()
        strings = AiutaImagePickerPhotoGalleryFeatureStrings.Default()
    }
    predefinedModels {
        icons = DefaultAiutaImageSelectorPredefinedModelFeatureIcons()
        strings = AiutaImageSelectorPredefinedModelFeatureStrings.Default()
    }
    uploadsHistory {
        strings =
            AiutaImagePickerUploadsHistoryFeatureStrings.Default(isPredefinedModelAvailable = true)
        styles = AiutaImagePickerUploadsHistoryFeatureStyles.Default()
    }
    images = DefaultAiutaImagePickerFeatureImages()
    strings = AiutaImagePickerFeatureStrings.Default()
}

package com.aiuta.fashionsdk.configuration.internal.validation.features.selector

import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.configuration.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.validateMapWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.logger.AiutaLogger

internal fun AiutaImageSelectorFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    // Features
    camera?.strings?.cameraButtonTakePhoto.validateStringWithSettings(
        propertyName = "cameraButtonTakePhoto",
        logger = logger,
        debugSettings = debugSettings,
    )
    camera?.strings?.cameraTitlePermission.validateStringWithSettings(
        propertyName = "cameraTitlePermission",
        logger = logger,
        debugSettings = debugSettings,
    )
    camera?.strings?.cameraDescriptionPermission.validateStringWithSettings(
        propertyName = "cameraDescriptionPermission",
        logger = logger,
        debugSettings = debugSettings,
    )
    camera?.strings?.cameraButtonPermissionOpenSettings.validateStringWithSettings(
        propertyName = "cameraButtonPermissionOpenSettings",
        logger = logger,
        debugSettings = debugSettings,
    )

    photoGallery.strings.galleryButtonSelectPhoto.validateStringWithSettings(
        "galleryButtonSelectPhoto",
        logger,
        debugSettings,
    )

    predefinedModels?.strings?.predefinedModelPageTitle.validateStringWithSettings(
        propertyName = "predefinedModelPageTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    predefinedModels?.strings?.predefinedModelOr.validateStringWithSettings(
        propertyName = "predefinedModelOr",
        logger = logger,
        debugSettings = debugSettings,
    )
    predefinedModels?.strings?.predefinedModelErrorEmptyModelsList.validateStringWithSettings(
        propertyName = "predefinedModelErrorEmptyModelsList",
        logger = logger,
        debugSettings = debugSettings,
    )
    predefinedModels?.strings?.predefinedModelCategories.validateMapWithSettings(
        "predefinedModelCategories",
        logger,
        debugSettings,
    )

    uploadsHistory?.strings?.uploadsHistoryTitle.validateStringWithSettings(
        propertyName = "uploadsHistoryTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    uploadsHistory?.strings?.uploadsHistoryButtonNewPhoto.validateStringWithSettings(
        propertyName = "uploadsHistoryButtonNewPhoto",
        logger = logger,
        debugSettings = debugSettings,
    )
    uploadsHistory?.strings?.uploadsHistoryButtonChangePhoto.validateStringWithSettings(
        propertyName = "uploadsHistoryButtonChangePhoto",
        logger = logger,
        debugSettings = debugSettings,
    )

    // General
    strings.imageSelectorTitleEmpty.validateStringWithSettings(
        propertyName = "imageSelectorTitleEmpty",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.imageSelectorDescriptionEmpty.validateStringWithSettings(
        propertyName = "imageSelectorDescriptionEmpty",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.imageSelectorButtonUploadImage.validateStringWithSettings(
        propertyName = "imageSelectorButtonUploadImage",
        logger = logger,
        debugSettings = debugSettings,
    )

    // Lists
    // General
    images.examples.validateListWithSettings("examples", logger, debugSettings)
}

package com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.selector

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings.validationList
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateMapWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.validateStringsWithSettings

internal fun AiutaImageSelectorFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    // Features
    camera?.strings?.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    photoGallery.strings.galleryButtonSelectPhoto.validateStringWithSettings(
        "galleryButtonSelectPhoto",
        logger,
        debugSettings,
    )

    predefinedModels?.strings?.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )
    predefinedModels?.strings?.predefinedModelCategories.validateMapWithSettings(
        "predefinedModelCategories",
        logger,
        debugSettings,
    )

    uploadsHistory?.strings?.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    // General
    strings.validationList.validateStringsWithSettings(
        logger = logger,
        debugSettings = debugSettings,
    )

    // Lists
    // General
    images.examples.validateListWithSettings("examples", logger, debugSettings)
}

package com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.features.tryon

import com.aiuta.fashionsdk.logger.AiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateListWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.internal.validation.validateStringWithSettings
import com.aiuta.fashionsdk.tryon.compose.domain.models.debug.AiutaDebugSettings

internal fun AiutaTryOnFeature.validateWithSettings(
    logger: AiutaLogger?,
    debugSettings: AiutaDebugSettings,
) {
    // Strings
    // Features
    loadingPage.strings.tryOnLoadingStatusUploadingImage.validateStringWithSettings(
        propertyName = "tryOnLoadingStatusUploadingImage",
        logger = logger,
        debugSettings = debugSettings,
    )
    loadingPage.strings.tryOnLoadingStatusScanningBody.validateStringWithSettings(
        propertyName = "tryOnLoadingStatusScanningBody",
        logger = logger,
        debugSettings = debugSettings,
    )
    loadingPage.strings.tryOnLoadingStatusGeneratingOutfit.validateStringWithSettings(
        propertyName = "tryOnLoadingStatusGeneratingOutfit",
        logger = logger,
        debugSettings = debugSettings,
    )

    fitDisclaimer?.strings?.tryOnFitTitle.validateStringWithSettings(
        propertyName = "tryOnFitTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    fitDisclaimer?.strings?.tryOnFitDescription.validateStringWithSettings(
        propertyName = "tryOnFitDescription",
        logger = logger,
        debugSettings = debugSettings,
    )
    fitDisclaimer?.strings?.tryOnFitButtonClose.validateStringWithSettings(
        propertyName = "tryOnFitButtonClose",
        logger = logger,
        debugSettings = debugSettings,
    )

    feedback?.strings?.tryOnFeedbackOptions?.forEachIndexed { index, string ->
        string.validateStringWithSettings(
            propertyName = "tryOnFeedbackOptions[$index]",
            logger = logger,
            debugSettings = debugSettings,
        )
    }
    feedback?.strings?.tryOnFeedbackTitle.validateStringWithSettings(
        propertyName = "tryOnFeedbackTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    feedback?.strings?.tryOnFeedbackButtonSkip.validateStringWithSettings(
        propertyName = "tryOnFeedbackButtonSkip",
        logger = logger,
        debugSettings = debugSettings,
    )
    feedback?.strings?.tryOnFeedbackButtonSend.validateStringWithSettings(
        propertyName = "tryOnFeedbackButtonSend",
        logger = logger,
        debugSettings = debugSettings,
    )
    feedback?.strings?.tryOnFeedbackGratitudeText.validateStringWithSettings(
        propertyName = "tryOnFeedbackGratitudeText",
        logger = logger,
        debugSettings = debugSettings,
    )

    feedback?.otherFeedback?.strings?.otherFeedbackTitle.validateStringWithSettings(
        propertyName = "otherFeedbackTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    feedback?.otherFeedback?.strings?.otherFeedbackButtonSend.validateStringWithSettings(
        propertyName = "otherFeedbackButtonSend",
        logger = logger,
        debugSettings = debugSettings,
    )
    feedback?.otherFeedback?.strings?.otherFeedbackButtonCancel.validateStringWithSettings(
        propertyName = "otherFeedbackButtonCancel",
        logger = logger,
        debugSettings = debugSettings,
    )
    feedback?.otherFeedback?.strings?.otherFeedbackOptionOther.validateStringWithSettings(
        propertyName = "otherFeedbackOptionOther",
        logger = logger,
        debugSettings = debugSettings,
    )

    generationsHistory?.strings?.generationsHistoryPageTitle.validateStringWithSettings(
        propertyName = "generationsHistoryPageTitle",
        logger = logger,
        debugSettings = debugSettings,
    )

    // General
    strings.tryOnPageTitle.validateStringWithSettings(
        propertyName = "tryOnPageTitle",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.tryOnPoweredByAiuta.validateStringWithSettings(
        propertyName = "tryOnPoweredByAiuta",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.tryOnDialogButtonInvalidImage.validateStringWithSettings(
        propertyName = "tryOnDialogButtonInvalidImage",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.tryOnDialogDescriptionInvalidImage.validateStringWithSettings(
        propertyName = "tryOnDialogDescriptionInvalidImage",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.tryOnButtonTryOn.validateStringWithSettings(
        propertyName = "tryOnButtonTryOn",
        logger = logger,
        debugSettings = debugSettings,
    )
    strings.tryOnButtonAddToCart.validateStringWithSettings(
        propertyName = "tryOnButtonAddToCart",
        logger = logger,
        debugSettings = debugSettings,
    )

    // Lists
    loadingPage.styles.loadingStatusBackgroundGradient?.validateListWithSettings(
        propertyName = "loadingStatusBackgroundGradient",
        logger = logger,
        debugSettings = debugSettings,
    )

    feedback?.strings?.tryOnFeedbackOptions.validateListWithSettings(
        propertyName = "tryOnFeedbackOptions",
        logger = logger,
        debugSettings = debugSettings,
    )

    styles.tryOnButtonGradient?.validateListWithSettings(
        propertyName = "tryOnButtonGradient",
        logger = logger,
        debugSettings = debugSettings,
    )
}

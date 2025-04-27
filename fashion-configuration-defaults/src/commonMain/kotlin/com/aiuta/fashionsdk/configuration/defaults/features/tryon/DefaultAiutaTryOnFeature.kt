package com.aiuta.fashionsdk.configuration.defaults.features.tryon

import com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.DefaultAiutaTryOnFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.disclaimer.DefaultAiutaTryOnFitDisclaimerFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.feedback.DefaultAiutaTryOnFeedbackFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.history.DefaultAiutaTryOnGenerationsHistoryFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.loading.DefaultAiutaTryOnLoadingPageFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.repicking.DefaultAiutaTryOnWithOtherPhotoFeatureIcons
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.tryon.cart.cart
import com.aiuta.fashionsdk.configuration.features.tryon.cart.handler.AiutaTryOnCartFeatureHandler
import com.aiuta.fashionsdk.configuration.features.tryon.cart.strings.AiutaTryOnCartFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.disclaimer.fitDisclaimer
import com.aiuta.fashionsdk.configuration.features.tryon.disclaimer.strings.AiutaTryOnFitDisclaimerFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.feedback
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.otherFeedback
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.other.strings.AiutaTryOnFeedbackOtherFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.strings.AiutaTryOnFeedbackFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.history.generationsHistory
import com.aiuta.fashionsdk.configuration.features.tryon.history.strings.AiutaTryOnGenerationsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.loading.loadingPage
import com.aiuta.fashionsdk.configuration.features.tryon.loading.strings.AiutaTryOnLoadingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.loading.styles.AiutaTryOnLoadingPageFeatureStyles
import com.aiuta.fashionsdk.configuration.features.tryon.other.otherPhoto
import com.aiuta.fashionsdk.configuration.features.tryon.strings.AiutaTryOnFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.styles.AiutaTryOnFeatureStyles
import com.aiuta.fashionsdk.configuration.features.tryon.toggles.AiutaTryOnFeatureToggles
import com.aiuta.fashionsdk.configuration.features.tryon.tryOn
import com.aiuta.fashionsdk.configuration.features.tryon.validation.inputImageValidation
import com.aiuta.fashionsdk.configuration.features.tryon.validation.strings.AiutaTryOnInputImageValidationFeatureStrings

public fun AiutaFeatures.Builder.defaultTryOn(
    cartHandler: AiutaTryOnCartFeatureHandler,
): AiutaFeatures.Builder = tryOn {
    loadingPage {
        icons = DefaultAiutaTryOnLoadingPageFeatureIcons()
        strings = AiutaTryOnLoadingPageFeatureStrings.Default()
        styles = AiutaTryOnLoadingPageFeatureStyles.Default()
    }
    cart {
        strings = AiutaTryOnCartFeatureStrings.Default()
        handler = cartHandler
    }
    inputImageValidation {
        strings = AiutaTryOnInputImageValidationFeatureStrings.Default()
    }
    fitDisclaimer {
        icons = DefaultAiutaTryOnFitDisclaimerFeatureIcons()
        strings = AiutaTryOnFitDisclaimerFeatureStrings.Default()
    }
    feedback {
        otherFeedback {
            strings = AiutaTryOnFeedbackOtherFeatureStrings.Default()
        }
        icons = DefaultAiutaTryOnFeedbackFeatureIcons()
        strings = AiutaTryOnFeedbackFeatureStrings.Default()
    }
    generationsHistory {
        icons = DefaultAiutaTryOnGenerationsHistoryFeatureIcons()
        strings = AiutaTryOnGenerationsHistoryFeatureStrings.Default()
    }
    otherPhoto {
        icons = DefaultAiutaTryOnWithOtherPhotoFeatureIcons()
    }
    icons = DefaultAiutaTryOnFeatureIcons()
    toggles = AiutaTryOnFeatureToggles.Default()
    strings = AiutaTryOnFeatureStrings.Default()
    styles = AiutaTryOnFeatureStyles.Default()
}

package com.aiuta.fashionsdk.tryon.compose.defaults.features.tryon

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.tryon.disclaimer.fitDisclaimer
import com.aiuta.fashionsdk.configuration.features.features.tryon.disclaimer.strings.AiutaTryOnFitDisclaimerFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.tryon.feedback.feedback
import com.aiuta.fashionsdk.configuration.features.features.tryon.feedback.other.otherFeedback
import com.aiuta.fashionsdk.configuration.features.features.tryon.feedback.other.strings.AiutaTryOnFeedbackOtherFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.tryon.feedback.strings.AiutaTryOnFeedbackFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.tryon.history.generationsHistory
import com.aiuta.fashionsdk.configuration.features.features.tryon.history.strings.AiutaTryOnGenerationsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.tryon.loading.loadingPage
import com.aiuta.fashionsdk.configuration.features.features.tryon.loading.strings.AiutaTryOnLoadingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.tryon.loading.styles.AiutaTryOnLoadingPageFeatureStyles
import com.aiuta.fashionsdk.configuration.features.features.tryon.repicking.repicking
import com.aiuta.fashionsdk.configuration.features.features.tryon.strings.AiutaTryOnFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.tryon.styles.AiutaTryOnFeatureStyles
import com.aiuta.fashionsdk.configuration.features.features.tryon.toggles.AiutaTryOnFeatureToggles
import com.aiuta.fashionsdk.configuration.features.features.tryon.tryOn
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.DefaultAiutaTryOnFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.disclaimer.DefaultAiutaTryOnFitDisclaimerFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.feedback.DefaultAiutaTryOnFeedbackFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.history.DefaultAiutaTryOnGenerationsHistoryFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.loading.DefaultAiutaTryOnLoadingPageFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.repicking.DefaultAiutaTryOnWithOtherPhotoFeatureIcons

public fun AiutaTryOnConfigurationFeatures.Builder.defaultTryOn() {
    tryOn {
        loadingPage {
            icons = DefaultAiutaTryOnLoadingPageFeatureIcons()
            strings = AiutaTryOnLoadingPageFeatureStrings.Default()
            styles = AiutaTryOnLoadingPageFeatureStyles.Default()
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
        repicking {
            icons = DefaultAiutaTryOnWithOtherPhotoFeatureIcons()
        }
        icons = DefaultAiutaTryOnFeatureIcons()
        toggles = AiutaTryOnFeatureToggles.Default()
        strings = AiutaTryOnFeatureStrings.Default()
        styles = AiutaTryOnFeatureStyles.Default()
    }
}

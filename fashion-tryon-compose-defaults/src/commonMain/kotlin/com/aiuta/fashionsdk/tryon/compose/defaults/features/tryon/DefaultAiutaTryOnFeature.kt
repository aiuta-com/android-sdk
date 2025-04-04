package com.aiuta.fashionsdk.tryon.compose.defaults.features.tryon

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.fitDisclaimer
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.strings.AiutaTryOnFitDisclaimerFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.feedback
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.other.otherFeedback
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.other.strings.AiutaTryOnFeedbackOtherFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.strings.AiutaTryOnFeedbackFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.generationsHistory
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.strings.AiutaTryOnGenerationsHistoryFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.loadingPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.strings.AiutaTryOnLoadingPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.repicking.repicking
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.strings.AiutaTryOnFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.styles.AiutaTryOnFeatureStyles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.toggles.AiutaTryOnFeatureToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.tryOn
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.DefaultAiutaTryOnFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.disclaimer.DefaultAiutaTryOnFitDisclaimerFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.feedback.DefaultAiutaTryOnFeedbackFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.history.DefaultAiutaTryOnGenerationsHistoryFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.loading.DefaultAiutaTryOnLoadingPageFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.repicking.DefaultAiutaTryOnRepickingFeatureIcons

public fun AiutaTryOnConfigurationFeatures.Builder.defaultTryOn() {
    tryOn {
        loadingPage {
            icons = DefaultAiutaTryOnLoadingPageFeatureIcons()
            strings = AiutaTryOnLoadingPageFeatureStrings.Default()
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
            icons = DefaultAiutaTryOnRepickingFeatureIcons()
        }
        icons = DefaultAiutaTryOnFeatureIcons()
        toggles = AiutaTryOnFeatureToggles.Default()
        strings = AiutaTryOnFeatureStrings.Default()
        styles = AiutaTryOnFeatureStyles.Default()
    }
}

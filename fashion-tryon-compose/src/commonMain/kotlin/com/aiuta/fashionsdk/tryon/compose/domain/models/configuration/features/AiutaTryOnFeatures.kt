package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

@Immutable
public class AiutaTryOnFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
) {
    public class Builder {
        private var welcomeScreen: AiutaWelcomeScreenFeature? = null

        public fun setWelcomeScreen(welcomeScreen: AiutaWelcomeScreenFeature): Builder {
            return apply { this.welcomeScreen = welcomeScreen }
        }

        public fun build(): AiutaTryOnFeatures {
            // Init default
            val internalWelcomeScreen = welcomeScreen

            return AiutaTryOnFeatures(
                welcomeScreen = internalWelcomeScreen,
            )
        }
    }
}

@Composable
@ReadOnlyComposable
internal fun AiutaTryOnConfiguration.welcomeScreenFeature(): AiutaWelcomeScreenFeature {
    return checkFeatureAvailability(
        name = "AiutaWelcomeScreenFeature",
        feature = features.welcomeScreen,
    )
}

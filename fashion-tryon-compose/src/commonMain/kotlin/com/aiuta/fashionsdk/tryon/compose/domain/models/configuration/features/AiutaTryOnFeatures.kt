package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.AiutaWelcomeScreenFeature

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


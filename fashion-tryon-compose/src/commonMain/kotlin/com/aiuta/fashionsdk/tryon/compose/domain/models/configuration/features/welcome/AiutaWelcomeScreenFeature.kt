package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.icons.AiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.images.AiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

public class AiutaWelcomeScreenFeature(
    public val images: AiutaWelcomeScreenFeatureImages,
    public val icons: AiutaWelcomeScreenFeatureIcons,
    public val strings: AiutaWelcomeScreenFeatureStrings,
    public val typography: AiutaWelcomeScreenFeatureTypography,
)

@Composable
@ReadOnlyComposable
internal fun strictWelcomeScreenFeature(): AiutaWelcomeScreenFeature {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return checkFeatureAvailability(
        name = "AiutaWelcomeScreenFeature",
        feature = aiutaConfiguration.features.welcomeScreen,
    )
}

internal fun AiutaTryOnConfiguration.isWelcomeScreenFeatureAvailable(): Boolean {
    return features.welcomeScreen != null
}

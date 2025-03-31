package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.icons.AiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.images.AiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

public class AiutaWelcomeScreenFeature(
    public val images: AiutaWelcomeScreenFeatureImages,
    public val icons: AiutaWelcomeScreenFeatureIcons,
    public val strings: AiutaWelcomeScreenFeatureStrings,
    public val typography: AiutaWelcomeScreenFeatureTypography,
): AiutaTryOnFeature

@Composable
@ReadOnlyComposable
internal fun AiutaTryOnConfiguration.welcomeScreenFeature(): AiutaWelcomeScreenFeature {
    return checkFeatureAvailability(
        name = "AiutaWelcomeScreenFeature",
        feature = features.welcomeScreen,
    )
}

internal fun AiutaTryOnConfiguration.isWelcomeScreenFeatureAvailable(): Boolean {
    return features.welcomeScreen != null
}

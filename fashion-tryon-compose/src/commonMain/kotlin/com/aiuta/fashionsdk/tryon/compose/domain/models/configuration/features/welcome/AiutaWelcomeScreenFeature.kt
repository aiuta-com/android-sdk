package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome

import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.icons.AiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.images.AiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography

public class AiutaWelcomeScreenFeature(
    public val images: AiutaWelcomeScreenFeatureImages,
    public val icons: AiutaWelcomeScreenFeatureIcons,
    public val strings: AiutaWelcomeScreenFeatureStrings,
    public val typography: AiutaWelcomeScreenFeatureTypography,
): AiutaTryOnFeature

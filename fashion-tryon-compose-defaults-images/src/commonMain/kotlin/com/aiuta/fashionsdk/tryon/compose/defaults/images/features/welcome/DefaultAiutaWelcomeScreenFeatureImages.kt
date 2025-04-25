package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.welcome

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.welcome_background
import com.aiuta.fashionsdk.configuration.features.features.welcome.images.AiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource

public class DefaultAiutaWelcomeScreenFeatureImages : AiutaWelcomeScreenFeatureImages {
    override val welcomeBackground: AiutaDrawableResource = AiutaComposeDrawableResource(Res.drawable.welcome_background)
}

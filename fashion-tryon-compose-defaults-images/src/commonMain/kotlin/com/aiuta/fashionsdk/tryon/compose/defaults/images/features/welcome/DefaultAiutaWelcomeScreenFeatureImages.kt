package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.welcome

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.welcome_background
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.images.AiutaWelcomeScreenFeatureImages

public class DefaultAiutaWelcomeScreenFeatureImages : AiutaWelcomeScreenFeatureImages {
    override val welcomeBackground: AiutaImage = AiutaResourceImage(Res.drawable.welcome_background)
}

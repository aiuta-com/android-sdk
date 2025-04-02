package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.selector

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.selector_empty_small_image_1
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.images.AiutaImageSelectorFeatureImages

public class DefaultAiutaImageSelectorFeatureImages : AiutaImageSelectorFeatureImages {
    override val examples: List<AiutaImage> = listOf(
        AiutaResourceImage(Res.drawable.selector_empty_small_image_1),
        AiutaResourceImage(Res.drawable.onboarding_good_image_1),
    )
}

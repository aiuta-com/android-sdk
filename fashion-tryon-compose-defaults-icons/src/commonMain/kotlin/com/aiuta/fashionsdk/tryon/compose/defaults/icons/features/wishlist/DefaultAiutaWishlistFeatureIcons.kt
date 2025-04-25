package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.wishlist

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_wishlist_24
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_wishlist_fill_24
import com.aiuta.fashionsdk.configuration.features.features.wishlist.icons.AiutaWishlistFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaWishlistFeatureIcons : AiutaWishlistFeatureIcons {
    override val wishlist24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_wishlist_24),
    )
    override val wishlistFill24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_wishlist_fill_24),
    )
}

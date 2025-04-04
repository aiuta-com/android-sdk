package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.wishlist

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_wishlist_24
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_wishlist_fill_24
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcon
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.icons.AiutaWishlistFeatureIcons

public class DefaultAiutaWishlistFeatureIcons : AiutaWishlistFeatureIcons {
    override val wishlist24: AiutaIcon = AiutaResourceIcon(Res.drawable.ic_wishlist_24)
    override val wishlistFill24: AiutaIcon = AiutaResourceIcon(Res.drawable.ic_wishlist_fill_24)
}

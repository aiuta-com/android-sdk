package com.aiuta.fashionsdk.configuration.defaults.icons.features.wishlist

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_wishlist_24
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_wishlist_fill_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.wishlist.icons.AiutaWishlistFeatureIcons

/**
 * Default implementation of [AiutaWishlistFeatureIcons].
 *
 * This class provides the default icon resources for the wishlist feature,
 * including both outlined and filled wishlist icons.
 *
 * @property wishlist24 24x24 pixel outlined wishlist icon
 * @property wishlistFill24 24x24 pixel filled wishlist icon
 */
public class DefaultAiutaWishlistFeatureIcons : AiutaWishlistFeatureIcons {
    override val wishlist24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_wishlist_24),
    )
    override val wishlistFill24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_wishlist_fill_24),
    )
}

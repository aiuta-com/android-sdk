package com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.icons.AiutaWishlistFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaWishlistFeature(
    public val icons: AiutaWishlistFeatureIcons,
    public val dataProvider: AiutaWishlistFeatureDataProvider,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaWishlistFeatureIcons? = null
        public var dataProvider: AiutaWishlistFeatureDataProvider? = null

        public override fun build(): AiutaWishlistFeature {
            val parentClass = "AiutaWishlistFeature"

            return AiutaWishlistFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                dataProvider = dataProvider.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "dataProvider",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnConfigurationFeatures.Builder.wishlist(
    block: AiutaWishlistFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    wishlist = AiutaWishlistFeature.Builder().apply(block).build()
}

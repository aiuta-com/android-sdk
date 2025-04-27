package com.aiuta.fashionsdk.configuration.features.wishlist

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.wishlist.icons.AiutaWishlistFeatureIcons
import com.aiuta.fashionsdk.configuration.features.wishlist.strings.AiutaWishlistFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaWishlistFeature private constructor(
    public val icons: AiutaWishlistFeatureIcons,
    public val strings: AiutaWishlistFeatureStrings,
    public val dataProvider: AiutaWishlistFeatureDataProvider,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaWishlistFeatureIcons? = null
        public var strings: AiutaWishlistFeatureStrings? = null
        public var dataProvider: AiutaWishlistFeatureDataProvider? = null

        public override fun build(): AiutaWishlistFeature {
            val parentClass = "AiutaWishlistFeature"

            return AiutaWishlistFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                dataProvider = dataProvider.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "dataProvider",
                ),
            )
        }
    }
}

public inline fun AiutaFeatures.Builder.wishlist(
    block: AiutaWishlistFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    wishlist = AiutaWishlistFeature.Builder().apply(block).build()
}

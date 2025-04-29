package com.aiuta.fashionsdk.configuration.features.tryon.loading

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.loading.icons.AiutaTryOnLoadingPageFeatureIcons
import com.aiuta.fashionsdk.configuration.features.tryon.loading.strings.AiutaTryOnLoadingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.loading.styles.AiutaTryOnLoadingPageFeatureStyles
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnLoadingPageFeature(
    public val icons: AiutaTryOnLoadingPageFeatureIcons,
    public val strings: AiutaTryOnLoadingPageFeatureStrings,
    public val styles: AiutaTryOnLoadingPageFeatureStyles,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaTryOnLoadingPageFeatureIcons? = null
        public var strings: AiutaTryOnLoadingPageFeatureStrings? = null
        public var styles: AiutaTryOnLoadingPageFeatureStyles? = null

        public override fun build(): AiutaTryOnLoadingPageFeature {
            val parentClass = "AiutaTryOnLoadingPageFeature"

            return AiutaTryOnLoadingPageFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                styles = styles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.loadingPage(
    block: AiutaTryOnLoadingPageFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    loadingPage = AiutaTryOnLoadingPageFeature.Builder().apply(block).build()
}

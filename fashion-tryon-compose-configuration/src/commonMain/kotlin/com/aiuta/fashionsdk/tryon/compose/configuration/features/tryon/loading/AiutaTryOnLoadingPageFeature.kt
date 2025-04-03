package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.icons.AiutaTryOnLoadingPageFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.strings.AiutaTryOnLoadingPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaTryOnLoadingPageFeature(
    public val icons: AiutaTryOnLoadingPageFeatureIcons,
    public val strings: AiutaTryOnLoadingPageFeatureStrings,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaTryOnLoadingPageFeatureIcons? = null
        public var strings: AiutaTryOnLoadingPageFeatureStrings? = null

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
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.loadingPage(
    block: AiutaTryOnLoadingPageFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    loadingPage = AiutaTryOnLoadingPageFeature.Builder().apply(block).build()
}

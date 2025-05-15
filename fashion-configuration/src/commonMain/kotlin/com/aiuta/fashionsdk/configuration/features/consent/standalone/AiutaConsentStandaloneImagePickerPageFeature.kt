package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.consent.standalone.icons.AiutaConsentStandaloneFeatureIcons
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.standalone.styles.AiutaConsentStandaloneFeatureStyles
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaConsentStandaloneImagePickerPageFeature(
    public override val strings: AiutaConsentStandaloneFeatureStrings,
    public override val data: AiutaConsentStandaloneFeatureData,
    public override val dataProvider: AiutaConsentStandaloneFeatureDataProvider,
    public override val icons: AiutaConsentStandaloneFeatureIcons,
    public override val styles: AiutaConsentStandaloneFeatureStyles,
) : AiutaConsentStandaloneFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaConsentStandaloneFeatureStrings? = null
        public var data: AiutaConsentStandaloneFeatureData? = null
        public var dataProvider: AiutaConsentStandaloneFeatureDataProvider? = null
        public var icons: AiutaConsentStandaloneFeatureIcons? = null
        public var styles: AiutaConsentStandaloneFeatureStyles? = null

        public override fun build(): AiutaConsentStandaloneImagePickerPageFeature {
            val parentClass = "AiutaConsentStandaloneImagePickerPageFeature"

            return AiutaConsentStandaloneImagePickerPageFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                data = data.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "data",
                ),
                dataProvider = dataProvider.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "dataProvider",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                styles = styles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
                ),
            )
        }
    }
}

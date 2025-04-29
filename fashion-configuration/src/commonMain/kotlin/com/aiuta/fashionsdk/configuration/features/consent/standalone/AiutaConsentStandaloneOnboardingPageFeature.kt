package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.consent.standalone.icons.AiutaConsentStandaloneFeatureIcons
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.standalone.styles.AiutaConsentStandaloneFeatureStyles
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaConsentStandaloneOnboardingPageFeature(
    public override val strings: AiutaConsentStandaloneOnboardingPageFeatureStrings,
    public override val data: AiutaConsentStandaloneOnboardingPageFeatureData,
    public override val dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider?,
    public override val icons: AiutaConsentStandaloneFeatureIcons,
    public override val styles: AiutaConsentStandaloneFeatureStyles,
) : AiutaConsentStandaloneFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaConsentStandaloneOnboardingPageFeatureStrings? = null
        public var data: AiutaConsentStandaloneOnboardingPageFeatureData? = null
        public var dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider? = null
        public var icons: AiutaConsentStandaloneFeatureIcons? = null
        public var styles: AiutaConsentStandaloneFeatureStyles? = null

        public override fun build(): AiutaConsentStandaloneOnboardingPageFeature {
            val parentClass = "AiutaConsentStandaloneOnboardingPageFeature"

            return AiutaConsentStandaloneOnboardingPageFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                data = data.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "data",
                ),
                dataProvider = this.dataProvider,
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

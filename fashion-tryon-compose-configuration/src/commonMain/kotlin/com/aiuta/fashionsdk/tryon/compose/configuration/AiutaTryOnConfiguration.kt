package com.aiuta.fashionsdk.tryon.compose.configuration

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.tryon

/**
 * Configuration of Aiuta Try on Flow
 *
 */
@Immutable
public class AiutaTryOnConfiguration private constructor(
    public val aiuta: Aiuta,
    public val features: AiutaTryOnConfigurationFeatures,
) {
    public val aiutaTryOn: AiutaTryOn by lazy { aiuta.tryon }
    public val aiutaAnalytic: InternalAiutaAnalytic by lazy { aiuta.internalAiutaAnalytic }

    /**
     * Public [Builder] for initialize [AiutaTryOnConfiguration] class
     */
    @AiutaDsl
    public class Builder {
        public var aiuta: Aiuta? = null
        public var features: AiutaTryOnConfigurationFeatures? = null

        public fun build(): AiutaTryOnConfiguration {
            val parentClass = "AiutaTryOnConfiguration"

            return AiutaTryOnConfiguration(
                aiuta = aiuta.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "aiuta",
                ),
                features = features.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "features",
                ),
            )
        }
    }
}

public inline fun aiutaTryOnConfiguration(
    block: AiutaTryOnConfiguration.Builder.() -> Unit,
): AiutaTryOnConfiguration = AiutaTryOnConfiguration.Builder().apply(block).build()

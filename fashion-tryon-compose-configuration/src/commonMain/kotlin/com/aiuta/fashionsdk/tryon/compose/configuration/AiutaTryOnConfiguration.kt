package com.aiuta.fashionsdk.tryon.compose.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.analytic.sendConfigurationEvent
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.compose.configuration.meta.DefaultHostMetadata
import com.aiuta.fashionsdk.tryon.compose.configuration.meta.HostMetadata
import com.aiuta.fashionsdk.tryon.compose.configuration.toggles.AiutaToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.toggles.DefaultAiutaToggles
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
    @Deprecated("Will be split by features")
    public val hostMetadata: HostMetadata,
    @Deprecated("Will be split by features")
    public val toggles: AiutaToggles,
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
        public var hostMetadata: HostMetadata? = null
        public var toggles: AiutaToggles? = null

        public fun build(): AiutaTryOnConfiguration {
            val parentClass = "AiutaTryOnConfiguration"

            // Default
            val internalToggles = toggles ?: DefaultAiutaToggles
            val internalHostMetadata = hostMetadata ?: DefaultHostMetadata

            return AiutaTryOnConfiguration(
                aiuta = aiuta.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "aiuta",
                ),
                features = features.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "features",
                ),
                hostMetadata = internalHostMetadata,
                toggles = internalToggles,
            ).also {
                it.sendConfigurationEvent()
            }
        }
    }
}

public inline fun aiutaTryOnConfiguration(
    block: AiutaTryOnConfiguration.Builder.() -> Unit,
): AiutaTryOnConfiguration = AiutaTryOnConfiguration.Builder().apply(block).build()

@Composable
public fun rememberAiutaTryOnConfiguration(
    block: AiutaTryOnConfiguration.Builder.() -> Unit,
): AiutaTryOnConfiguration = remember { aiutaTryOnConfiguration(block) }

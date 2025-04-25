package com.aiuta.fashionsdk.configuration.features.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl

@Immutable
public interface AiutaTryOnConfigurationFeature {

    @AiutaDsl
    public interface Builder {
        public fun build(): AiutaTryOnConfigurationFeature
    }
}

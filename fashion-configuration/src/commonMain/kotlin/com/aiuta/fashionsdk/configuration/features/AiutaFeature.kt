package com.aiuta.fashionsdk.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl

@Immutable
public interface AiutaFeature {

    @AiutaDsl
    public interface Builder {
        public fun build(): AiutaFeature
    }
}

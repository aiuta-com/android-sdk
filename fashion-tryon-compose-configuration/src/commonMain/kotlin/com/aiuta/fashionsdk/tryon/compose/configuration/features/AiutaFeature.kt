package com.aiuta.fashionsdk.tryon.compose.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl

@Immutable
public interface AiutaFeature {

    @AiutaDsl
    public interface Builder {
        public fun build(): AiutaFeature
    }
}

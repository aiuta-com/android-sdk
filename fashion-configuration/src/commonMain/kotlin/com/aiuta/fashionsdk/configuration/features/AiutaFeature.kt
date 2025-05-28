package com.aiuta.fashionsdk.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl

/**
 * Base interface for all Aiuta SDK feature configurations.
 * 
 * This interface serves as the foundation for all feature configuration classes
 * in the Aiuta SDK. Each feature implements this interface to provide a consistent
 * configuration pattern across the SDK.
 * 
 * Features are immutable configuration objects that define the behavior and
 * appearance of specific SDK functionality such as image picking, try-on,
 * sharing, and more.
 * 
 * @see AiutaFeatures
 */
@Immutable
public interface AiutaFeature {

    /**
     * Base interface for feature builders.
     * 
     * All feature builders must implement this interface to provide a consistent
     * building pattern across the SDK. Builders use the DSL marker annotation
     * to provide type-safe configuration syntax.
     */
    @AiutaDsl
    public interface Builder {
        /**
         * Builds the feature instance with the configured properties.
         * 
         * @return The configured feature instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaFeature
    }
}

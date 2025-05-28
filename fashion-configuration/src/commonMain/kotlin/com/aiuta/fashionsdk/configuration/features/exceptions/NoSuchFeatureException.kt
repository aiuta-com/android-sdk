package com.aiuta.fashionsdk.configuration.features.exceptions

/**
 * Exception thrown when attempting to access a feature that is not supported or not properly configured.
 * 
 * This exception is thrown when the SDK attempts to provide a feature that either:
 * - Has not been properly configured
 * - Is not supported in the current context
 * - Has been disabled
 * 
 * @property featureName Name of the feature that was not found
 */
public class NoSuchFeatureException(featureName: String?) :
    IllegalStateException(
        "$featureName: Such feature is not supported with provideFeature(). Please, report this as issue.",
    )

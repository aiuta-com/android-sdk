package com.aiuta.fashionsdk.tryon.compose.configuration.exceptions

public class NoSuchFeatureException(featureName: String?) :
    IllegalStateException(
        "$featureName: Such feature is not supported with provideFeature(). Please, report this as issue.",
    )

package com.aiuta.fashionsdk.configuration.features.exceptions

public class NoSuchFeatureException(featureName: String?) :
    IllegalStateException(
        "$featureName: Such feature is not supported with provideFeature(). Please, report this as issue.",
    )

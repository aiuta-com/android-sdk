package com.aiuta.fashionsdk.tryon.compose.configuration.exceptions

public class NoSuchFeatureException :
    IllegalStateException(
        "Such feature is not supported with provideFeature(). Please, report this as issue.",
    )

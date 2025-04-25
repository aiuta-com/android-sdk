package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaFeatures

@Composable
@ReadOnlyComposable
internal inline fun <reified T : AiutaFeature> provideFeature(): T? {
    val features = LocalAiutaFeatures.current
    return features.provideFeature()
}

@Composable
@ReadOnlyComposable
internal inline fun <reified T : AiutaFeature> strictProvideFeature(): T {
    val features = LocalAiutaFeatures.current
    return features.strictProvideFeature()
}

@Composable
@ReadOnlyComposable
internal inline fun <reified T : AiutaFeature> isFeatureInitialize(): Boolean {
    val features = LocalAiutaFeatures.current
    return features.isFeatureInitialize<T>()
}

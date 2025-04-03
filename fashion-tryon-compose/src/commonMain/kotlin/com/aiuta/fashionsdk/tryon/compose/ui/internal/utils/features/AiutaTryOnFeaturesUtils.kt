package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration

@Composable
@ReadOnlyComposable
internal inline fun <reified T : AiutaTryOnConfigurationFeature> provideFeature(): T? {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return aiutaConfiguration.features.provideFeature()
}

@Composable
@ReadOnlyComposable
internal inline fun <reified T : AiutaTryOnConfigurationFeature> strictProvideFeature(): T {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return aiutaConfiguration.features.strictProvideFeature()
}

internal inline fun <reified T : AiutaTryOnConfigurationFeature> AiutaTryOnConfiguration.isFeatureInitialize(): Boolean = features.isFeatureInitialize<T>()

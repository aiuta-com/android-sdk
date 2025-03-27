package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.backhandler

import androidx.compose.runtime.Composable

// TODO Migrate to Jetbrains realization after release 1.8.0
@Composable
internal expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)

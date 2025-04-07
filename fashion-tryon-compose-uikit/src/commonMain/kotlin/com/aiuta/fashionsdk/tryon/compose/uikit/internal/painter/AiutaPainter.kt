package com.aiuta.fashionsdk.tryon.compose.uikit.internal.painter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaDrawableResource

@Composable
internal expect fun painterResource(drawableResource: AiutaDrawableResource): Painter

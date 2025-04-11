package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.coil

import coil3.PlatformContext
import com.aiuta.fashionsdk.context.AiutaPlatformContext

internal actual fun PlatformContext.toAiutaPlatformContext(): AiutaPlatformContext = AiutaPlatformContext.INSTANCE

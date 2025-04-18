package com.aiuta.fashionsdk.compose.core.context

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.context.AiutaPlatformContext

public actual val LocalAiutaPlatformContext: ProvidableCompositionLocal<AiutaPlatformContext> = staticCompositionLocalOf {
    AiutaPlatformContext.INSTANCE
}

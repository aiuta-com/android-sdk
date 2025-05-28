package com.aiuta.fashionsdk.compose.core.context

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.context.AiutaPlatformContext

/**
 * Non-Android implementation of [LocalAiutaPlatformContext].
 * 
 * For platforms other than Android, this provides a static composition local
 * that defaults to [AiutaPlatformContext.INSTANCE]. This allows the Aiuta SDK
 * to work on platforms that don't require platform-specific context.
 * 
 * 
 * @see AiutaPlatformContext.INSTANCE
 * @see staticCompositionLocalOf
 */
public actual val LocalAiutaPlatformContext: ProvidableCompositionLocal<AiutaPlatformContext> = staticCompositionLocalOf {
    AiutaPlatformContext.INSTANCE
}

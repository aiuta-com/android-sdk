package com.aiuta.fashionsdk.compose.core.context

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.context.AiutaPlatformContext

/**
 * Android implementation of [LocalAiutaPlatformContext].
 * 
 * On Android, this property delegates to Compose's [LocalContext], providing
 * access to the Android Context within Compose UI components. This allows
 * the Aiuta SDK to access Android-specific functionality and resources.
 * 
 * 
 * @see LocalContext
 * @see AiutaPlatformContext
 */
public actual inline val LocalAiutaPlatformContext: ProvidableCompositionLocal<AiutaPlatformContext>
    get() = LocalContext

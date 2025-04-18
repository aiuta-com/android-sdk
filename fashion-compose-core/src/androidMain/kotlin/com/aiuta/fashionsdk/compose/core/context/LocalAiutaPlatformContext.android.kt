package com.aiuta.fashionsdk.compose.core.context

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.context.AiutaPlatformContext

public actual inline val LocalAiutaPlatformContext: ProvidableCompositionLocal<AiutaPlatformContext>
    get() = LocalContext

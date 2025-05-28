package com.aiuta.fashionsdk.compose.core.context

import androidx.compose.runtime.ProvidableCompositionLocal
import com.aiuta.fashionsdk.context.AiutaPlatformContext

/**
 * Composition local for accessing the Aiuta platform context within Compose UI.
 *
 * This composition local provides access to the platform-specific context required
 * by the Aiuta SDK. The implementation varies by platform:
 * - Android: Provides access to Android Context via LocalContext
 * - Other platforms: Provides a static instance for platforms without context requirements
 *
 *
 * @see AiutaPlatformContext
 */
public expect val LocalAiutaPlatformContext: ProvidableCompositionLocal<AiutaPlatformContext>

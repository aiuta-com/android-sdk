package com.aiuta.fashionsdk.compose.tokens.gradient

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

/**
 * Public class, for customization of gradients, used in SDK
 *
 * @see AiutaTheme
 */
@Immutable
public class AiutaGradients(
    public val loadingAnimation: List<Color>,
    public val tryOnButtonBackground: List<Color>,
)

/**
 * Default gradients for SDK
 *
 * @see AiutaTheme
 * @see AiutaGradients
 */
public val DefaultAiutaGradients: AiutaGradients by lazy { aiutaGradients() }

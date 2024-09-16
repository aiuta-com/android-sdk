package com.aiuta.fashionsdk.compose.tokens.gradient

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// TODO Add docs
@Immutable
public class AiutaGradients(
    public val loadingAnimation: List<Color>,
)

public val DefaultAiutaGradients: AiutaGradients by lazy { aiutaGradients() }

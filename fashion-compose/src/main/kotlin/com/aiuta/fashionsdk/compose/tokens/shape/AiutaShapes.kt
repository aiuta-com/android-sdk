package com.aiuta.fashionsdk.compose.tokens.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable

// TODO Add docs
@Immutable
public class AiutaShapes internal constructor(
    // Images
    public val mainImage: CornerBasedShape,
    public val previewImage: CornerBasedShape,
    public val historyImage: CornerBasedShape,
    // Bottom sheet
    public val bottomSheet: CornerBasedShape,
    // Buttons
    public val buttonL: CornerBasedShape,
    public val buttonM: CornerBasedShape,
)

public val DefaultAiutaShapes: AiutaShapes by lazy { aiutaShapes() }

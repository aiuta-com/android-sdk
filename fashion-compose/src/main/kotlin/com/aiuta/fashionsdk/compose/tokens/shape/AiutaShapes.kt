package com.aiuta.fashionsdk.compose.tokens.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable

// TODO Add docs
@Immutable
public class AiutaShapes internal constructor(
    // Images
    public val mainImageShape: CornerBasedShape,
    public val previewImageShape: CornerBasedShape,
    public val historyImageShape: CornerBasedShape,
    // Bottom sheet
    public val bottomSheetShape: CornerBasedShape,
    // Buttons
    public val buttonLShape: CornerBasedShape,
    public val buttonMShape: CornerBasedShape,
)

public val DefaultAiutaShapes: AiutaShapes by lazy { aiutaShapes() }

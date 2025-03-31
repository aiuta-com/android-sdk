package com.aiuta.fashionsdk.compose.tokens.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable

/**
 * Public class for providing shape tokens in the SDK.
 *
 * This class allows the host application to override the default shapes used by the SDK,
 * enabling a more personalized user experience.
 *
 * The shapes are categorized by their usage (e.g., Images, Bottom sheet, Buttons).
 */
@Immutable
public class AiutaShapes internal constructor(
    // Images
    public val mainImage: CornerBasedShape,
    public val onboardingImage: CornerBasedShape,
    public val previewImage: CornerBasedShape,
    // Bottom sheet
    public val bottomSheet: CornerBasedShape,
    // Buttons
    public val buttonL: CornerBasedShape,
    public val buttonM: CornerBasedShape,
)

public val DefaultAiutaShapes: AiutaShapes by lazy { aiutaShapes() }

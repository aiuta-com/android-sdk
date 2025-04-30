package com.aiuta.fashionsdk.configuration.features.tryon.loading.styles

import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.configuration.features.styles.AiutaButtonsWithOutlineStyle

public interface AiutaTryOnLoadingPageFeatureStyles {
    public val loadingStatusBackgroundGradient: List<Color>?
    public val loadingStatusStyle: AiutaButtonsWithOutlineStyle

    public class Default : AiutaTryOnLoadingPageFeatureStyles {
        override val loadingStatusBackgroundGradient: List<Color> = listOf(
            Color(0xFF4000FF),
            Color.Transparent,
        )
        override val loadingStatusStyle: AiutaButtonsWithOutlineStyle = AiutaButtonsWithOutlineStyle.BLURRED
    }
}

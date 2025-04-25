package com.aiuta.fashionsdk.configuration.features.features.tryon.loading.styles

import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.configuration.features.features.styles.AiutaButtonsStyle

public interface AiutaTryOnLoadingPageFeatureStyles {
    public val loadingStatusBackgroundGradient: List<Color>?
    public val loadingStatusStyle: AiutaButtonsStyle

    public class Default : AiutaTryOnLoadingPageFeatureStyles {
        override val loadingStatusBackgroundGradient: List<Color> = listOf(
            Color(0xFF4000FF),
            Color.Transparent,
        )
        override val loadingStatusStyle: AiutaButtonsStyle = AiutaButtonsStyle.BLURRED
    }
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem

@Immutable
internal sealed interface NavigationBottomSheetScreen {
    public object IDLE : NavigationBottomSheetScreen

    public class ImagePicker(
        public val originPageId: AiutaAnalyticPageId,
    ) : NavigationBottomSheetScreen

    public class FitDisclaimer(
        public val text: String,
    ) : NavigationBottomSheetScreen

    public class Feedback(
        public val title: String,
        public val options: List<String>,
        public val itemIndex: Int,
        public val extraOption: String? = null,
        public val extraOptionTitle: String? = null,
    ) : NavigationBottomSheetScreen {
        val isExtraVisible: Boolean = extraOption != null && extraOptionTitle != null
    }

    public class ExtraFeedback(
        public val extraOptionTitle: String,
        public val itemIndex: Int,
        public val optionIndex: Int,
    ) : NavigationBottomSheetScreen

    public class SKUInfo(
        public val primaryButtonState: PrimaryButtonState,
        public val skuItem: SKUItem,
        public val originPageId: AiutaAnalyticPageId,
    ) : NavigationBottomSheetScreen {
        public enum class PrimaryButtonState {
            ADD_TO_CART,

            TRY_ON,
        }
    }

    public object GeneratedOperations : NavigationBottomSheetScreen
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem

@Immutable
internal sealed interface NavigationBottomSheetScreen {
    public object IDLE : NavigationBottomSheetScreen

    public class ImagePicker(
        public val originPageId: AiutaAnalyticPageId,
    ) : NavigationBottomSheetScreen

    public object FitDisclaimer : NavigationBottomSheetScreen

    public object Feedback : NavigationBottomSheetScreen

    public class ExtraFeedback(
        public val optionIndex: Int,
    ) : NavigationBottomSheetScreen

    public class ProductInfo(
        public val primaryButtonState: PrimaryButtonState,
        public val productItem: ProductItem,
        public val originPageId: AiutaAnalyticPageId,
    ) : NavigationBottomSheetScreen {
        public enum class PrimaryButtonState {
            ADD_TO_CART,

            TRY_ON,
        }
    }

    public object GeneratedOperations : NavigationBottomSheetScreen
}

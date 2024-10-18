package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.RESULTS_EVENT)
public class AiutaAnalyticsResultsEvent(
    @SerialName("event")
    public val event: AiutaAnalyticsResultsEventType,
    @SerialName("productId")
    public val productId: String,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticsResultsEventType {
    @SerialName("resultShared")
    RESULT_SHARED,

    @SerialName("productAddToWishlist")
    PRODUCT_ADD_TO_WISHLIST,

    @SerialName("productAddToCart")
    PRODUCT_ADD_TO_CART,

    @SerialName("pickOtherPhoto")
    PICK_OTHER_PHOTO,
}

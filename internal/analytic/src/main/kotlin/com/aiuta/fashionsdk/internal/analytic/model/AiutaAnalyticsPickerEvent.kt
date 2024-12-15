package com.aiuta.fashionsdk.internal.analytic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(InternalAnalyticEvent.EventType.PICKER_EVENT)
public class AiutaAnalyticsPickerEvent(
    @SerialName("event")
    public val event: AiutaAnalyticsPickerEventType,
    @SerialName("pageId")
    public override val pageId: AiutaAnalyticPageId?,
    @SerialName("productId")
    public override val productId: String?,
) : ExternalAnalyticEvent

@Serializable
public enum class AiutaAnalyticsPickerEventType {
    @SerialName("cameraOpened")
    CAMERA_OPENED,

    @SerialName("newPhotoTaken")
    NEW_PHOTO_TAKEN,

    @SerialName("photoGalleryOpened")
    PHOTO_GALLERY_OPENED,

    @SerialName("galleryPhotoSelected")
    GALLERY_PHOTO_SELECTED,

    @SerialName("uploadsHistoryOpened")
    UPLOADS_HISTORY_OPENED,

    @SerialName("uploadedPhotoSelected")
    UPLOADED_PHOTO_SELECTED,

    @SerialName("uploadedPhotoDeleted")
    UPLOADED_PHOTO_DELETED,
}

package com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Data provider for dynamic data from host like
 * generated/uploaded images
 */
public class AiutaDataProvider(
    // Consent
    public val isUserConsentObtainedFlow: StateFlow<Boolean>,
    public val obtainUserConsentAction: (List<SupplementaryConsent>) -> Unit,
    // Generated images
    public val generatedImagesFlow: StateFlow<List<AiutaHistoryImage>>,
    public val addGeneratedImagesAction: (skuId: String, images: List<AiutaHistoryImage>) -> Unit,
    public val isErrorDeletingGeneratedImagesFlow: SharedFlow<Boolean>,
    public val deleteGeneratedImagesAction: (List<AiutaHistoryImage>) -> Unit,
    // Uploaded images
    public val uploadedImagesFlow: StateFlow<List<AiutaHistoryImage>>,
    public val addUploadedImagesAction: (List<AiutaHistoryImage>) -> Unit,
    public val isErrorDeletingUploadedImagesFlow: SharedFlow<Boolean>,
    public val deleteUploadedImagesAction: (List<AiutaHistoryImage>) -> Unit,
    public val selectUploadedImageAction: (AiutaHistoryImage) -> Unit,
)

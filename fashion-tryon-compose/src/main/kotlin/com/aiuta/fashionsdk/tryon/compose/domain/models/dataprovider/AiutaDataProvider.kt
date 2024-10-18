package com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider

import kotlinx.coroutines.flow.StateFlow

public class AiutaDataProvider(
    // Consent
    public val isUserConsentObtainedFlow: StateFlow<Boolean>,
    public val obtainUserConsentAction: () -> Unit,
    // Generated images
    public val generatedImagesFlow: StateFlow<List<AiutaGeneratedImage>>,
    public val addGeneratedImagesAction: (List<AiutaGeneratedImage>) -> Unit,
    public val deleteGeneratedImagesAction: (List<AiutaGeneratedImage>) -> Unit,
    // Uploaded images
    public val uploadedImagesFlow: StateFlow<List<AiutaUploadedImage>>,
    public val addUploadedImagesAction: (List<AiutaUploadedImage>) -> Unit,
    public val deleteUploadedImagesAction: (List<AiutaUploadedImage>) -> Unit,
    public val selectUploadedImageAction: (AiutaUploadedImage) -> Unit,
)

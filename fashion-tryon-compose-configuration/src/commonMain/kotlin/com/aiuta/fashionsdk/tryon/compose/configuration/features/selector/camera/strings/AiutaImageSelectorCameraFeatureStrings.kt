package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaImageSelectorCameraFeatureStrings {
    public val cameraButtonTakePhoto: String
    public val cameraTitlePermission: String
    public val cameraDescriptionPermission: String
    public val cameraButtonPermissionOpenSettings: String

    public class Default : AiutaImageSelectorCameraFeatureStrings {
        override val cameraButtonTakePhoto: String = "Take a photo"
        override val cameraTitlePermission: String = "Camera permission"
        override val cameraDescriptionPermission: String = "Please allow access to the camera in the application settings."
        override val cameraButtonPermissionOpenSettings: String = "Settings"
    }
}

internal val AiutaImageSelectorCameraFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "cameraButtonTakePhoto",
            string = cameraButtonTakePhoto,
        ),
        AiutaStringValidationContainer(
            propertyName = "cameraTitlePermission",
            string = cameraTitlePermission,
        ),
        AiutaStringValidationContainer(
            propertyName = "cameraDescriptionPermission",
            string = cameraDescriptionPermission,
        ),
        AiutaStringValidationContainer(
            propertyName = "cameraButtonPermissionOpenSettings",
            string = cameraButtonPermissionOpenSettings,
        ),
    )

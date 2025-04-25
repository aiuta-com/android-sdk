package com.aiuta.fashionsdk.configuration.features.picker.camera.strings

public interface AiutaImagePickerCameraFeatureStrings {
    public val cameraButtonTakePhoto: String
    public val cameraTitlePermission: String
    public val cameraDescriptionPermission: String
    public val cameraButtonPermissionOpenSettings: String

    public class Default : AiutaImagePickerCameraFeatureStrings {
        override val cameraButtonTakePhoto: String = "Take a photo"
        override val cameraTitlePermission: String = "Camera permission"
        override val cameraDescriptionPermission: String = "Please allow access to the camera in the application settings."
        override val cameraButtonPermissionOpenSettings: String = "Settings"
    }
}

package com.aiuta.fashionsdk.configuration.features.picker.camera.strings

public interface AiutaImagePickerCameraFeatureStrings {
    public val cameraButtonTakePhoto: String
    public val cameraPermissionTitle: String
    public val cameraPermissionDescription: String
    public val cameraPermissionButtonOpenSettings: String

    public class Default : AiutaImagePickerCameraFeatureStrings {
        override val cameraButtonTakePhoto: String = "Take a photo"
        override val cameraPermissionTitle: String = "Camera permission"
        override val cameraPermissionDescription: String = "Please allow access to the camera in the application settings."
        override val cameraPermissionButtonOpenSettings: String = "Settings"
    }
}

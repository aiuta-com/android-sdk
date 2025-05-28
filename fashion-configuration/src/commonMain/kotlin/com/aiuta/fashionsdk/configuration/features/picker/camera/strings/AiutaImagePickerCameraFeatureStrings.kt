package com.aiuta.fashionsdk.configuration.features.picker.camera.strings

/**
 * Interface defining text strings used in the camera feature.
 *
 * This interface provides strings for buttons and permission-related messages
 * displayed in the camera interface.
 */
public interface AiutaImagePickerCameraFeatureStrings {
    /**
     * Text for the button that triggers the camera capture.
     */
    public val cameraButtonTakePhoto: String

    /**
     * Title for the camera permission request dialog.
     */
    public val cameraPermissionTitle: String

    /**
     * Description text explaining why camera permission is needed.
     */
    public val cameraPermissionDescription: String

    /**
     * Text for the button that opens the app settings to grant camera permission.
     */
    public val cameraPermissionButtonOpenSettings: String

    /**
     * Default implementation of [AiutaImagePickerCameraFeatureStrings].
     *
     * Provides standard English text for the camera interface.
     */
    public class Default : AiutaImagePickerCameraFeatureStrings {
        override val cameraButtonTakePhoto: String = "Take a photo"
        override val cameraPermissionTitle: String = "Camera permission"
        override val cameraPermissionDescription: String = "Please allow access to the camera in the application settings."
        override val cameraPermissionButtonOpenSettings: String = "Settings"
    }
}

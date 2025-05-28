package com.aiuta.fashionsdk.configuration.features.picker.images

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource

/**
 * Interface defining image resources used in the image picker feature.
 *
 * This interface provides a list of example images that can be displayed
 * in the image picker UI, such as sample photos or suggestions.
 */
public interface AiutaImagePickerFeatureImages {
    /**
     * List of drawable resources representing example images for the image picker.
     */
    public val examples: List<AiutaDrawableResource>
}

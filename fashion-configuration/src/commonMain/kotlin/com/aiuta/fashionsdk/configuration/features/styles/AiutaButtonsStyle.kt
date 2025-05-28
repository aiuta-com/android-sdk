package com.aiuta.fashionsdk.configuration.features.styles

/**
 * Enum class defining the available button styles in the fashion SDK.
 * 
 * These styles determine the visual appearance of buttons throughout the app.
 */
public enum class AiutaButtonsStyle {
    /**
     * Primary button style with solid background and high contrast.
     */
    PRIMARY,

    /**
     * Blurred button style with translucent background.
     */
    BLURRED
}

/**
 * Enum class defining button styles that support outline variations.
 * 
 * These styles extend the basic button styles with additional outline options.
 */
public enum class AiutaButtonsWithOutlineStyle {
    /**
     * Primary button style with solid background and high contrast.
     */
    PRIMARY,

    /**
     * Blurred button style with translucent background.
     */
    BLURRED,

    /**
     * Blurred button style with an additional outline border.
     */
    BLURRED_WITH_OUTLINE
}

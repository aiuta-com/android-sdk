package com.aiuta.fashionsdk.configuration.features.consent.models

/**
 * Enum class defining the types of consent in the fashion SDK.
 *
 * This enum represents different ways in which user consent can be collected,
 * ranging from implicit to explicit consent, with various UI and behavioral requirements.
 */
public enum class AiutaConsentType {
    /**
     * Implicit consent without a checkbox.
     * User consent is assumed by continuing to use the feature.
     */
    IMPLICIT_WITHOUT_CHECKBOX,

    /**
     * Implicit consent with a checkbox.
     * User consent is assumed by continuing, but a checkbox is shown for acknowledgment.
     */
    IMPLICIT_WITH_CHECKBOX,

    /**
     * Explicit consent that is required.
     * User must actively agree to proceed, and cannot continue without consent.
     */
    EXPLICIT_REQUIRED,

    /**
     * Explicit consent that is optional.
     * User can choose to agree or decline, and can proceed either way.
     */
    EXPLICIT_OPTIONAL,
}

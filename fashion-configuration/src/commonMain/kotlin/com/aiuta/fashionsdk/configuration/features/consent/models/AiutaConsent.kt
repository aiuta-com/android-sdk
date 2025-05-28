package com.aiuta.fashionsdk.configuration.features.consent.models

/**
 * Data class representing a consent item in the fashion SDK.
 *
 * This class defines the structure of a consent item, including its identifier,
 * HTML-formatted content, and type.
 *
 * @property id Unique identifier for the consent item
 * @property consentHtml HTML-formatted text explaining the consent terms and conditions
 * @property type Type of consent, determining its behavior and requirements
 */
public class AiutaConsent(
    public val id: String,
    public val consentHtml: String,
    public val type: AiutaConsentType,
)

package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaTryOnFeatureStrings {
    public val tryOnPageTitle: String
    public val tryOnPoweredByAiuta: String
    public val tryOnDialogButtonInvalidImage: String
    public val tryOnDialogDescriptionInvalidImage: String
    public val tryOnButtonTryOn: String
    public val tryOnButtonAddToCart: String

    public class Default : AiutaTryOnFeatureStrings {
        override val tryOnPageTitle: String = "Virtual Try-on"
        override val tryOnPoweredByAiuta: String = "Powered by Aiuta"
        override val tryOnDialogButtonInvalidImage: String = "Change photo"
        override val tryOnDialogDescriptionInvalidImage: String = "We couldn’t detect anyone in this photo"
        override val tryOnButtonTryOn: String = "Try on"
        override val tryOnButtonAddToCart: String = "Add to cart"
    }
}

internal val AiutaTryOnFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "tryOnPageTitle",
            string = tryOnPageTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnPoweredByAiuta",
            string = tryOnPoweredByAiuta,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnDialogButtonInvalidImage",
            string = tryOnDialogButtonInvalidImage,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnDialogDescriptionInvalidImage",
            string = tryOnDialogDescriptionInvalidImage,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnButtonTryOn",
            string = tryOnButtonTryOn,
        ),
        AiutaStringValidationContainer(
            propertyName = "tryOnButtonAddToCart",
            string = tryOnButtonAddToCart,
        ),
    )

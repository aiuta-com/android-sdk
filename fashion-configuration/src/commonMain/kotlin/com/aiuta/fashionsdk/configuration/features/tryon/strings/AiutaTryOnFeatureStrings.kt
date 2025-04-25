package com.aiuta.fashionsdk.configuration.features.tryon.strings

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

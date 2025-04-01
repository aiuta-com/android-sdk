package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.strings

public interface AiutaImageSelectorPredefinedModelStrings {
    public val predefinedModelPageTitle: String
    public val predefinedModelOr: String
    public val predefinedModelCategories: Map<String, String>

    public class Default : AiutaImageSelectorPredefinedModelStrings {
        override val predefinedModelPageTitle: String = "Select your model"
        override val predefinedModelOr: String = "Or"
        override val predefinedModelCategories: Map<String, String> = buildMap {
            put(key = "woman", value = "Woman")
            put(key = "man", value = "Man")
        }
    }
}

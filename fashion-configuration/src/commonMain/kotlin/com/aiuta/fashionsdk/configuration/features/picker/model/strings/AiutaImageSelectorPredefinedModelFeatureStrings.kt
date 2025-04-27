package com.aiuta.fashionsdk.configuration.features.picker.model.strings

public interface AiutaImageSelectorPredefinedModelFeatureStrings {
    public val predefinedModelPageTitle: String
    public val predefinedModelOr: String
    public val predefinedModelErrorEmptyModelsList: String
    public val predefinedModelCategories: Map<String, String>

    public class Default : AiutaImageSelectorPredefinedModelFeatureStrings {
        override val predefinedModelPageTitle: String = "Select your model"
        override val predefinedModelOr: String = "Or"
        override val predefinedModelErrorEmptyModelsList: String = "The models list is empty"
        override val predefinedModelCategories: Map<String, String> = buildMap {
            put(key = "woman", value = "Woman")
            put(key = "man", value = "Man")
        }
    }
}

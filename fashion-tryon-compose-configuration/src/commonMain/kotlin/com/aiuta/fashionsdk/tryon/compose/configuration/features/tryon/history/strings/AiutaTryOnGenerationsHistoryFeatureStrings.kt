package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaTryOnGenerationsHistoryFeatureStrings {
    public val generationsHistoryPageTitle: String
    public val generationsHistoryButtonSelect: String

    public class Default : AiutaTryOnGenerationsHistoryFeatureStrings {
        override val generationsHistoryPageTitle: String = "History"
        override val generationsHistoryButtonSelect: String = "Select"
    }
}

internal val AiutaTryOnGenerationsHistoryFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "generationsHistoryPageTitle",
            string = generationsHistoryPageTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "generationsHistoryButtonSelect",
            string = generationsHistoryButtonSelect,
        ),
    )

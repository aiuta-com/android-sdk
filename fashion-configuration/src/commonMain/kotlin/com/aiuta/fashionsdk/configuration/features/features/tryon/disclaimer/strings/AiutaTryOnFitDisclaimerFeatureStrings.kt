package com.aiuta.fashionsdk.configuration.features.features.tryon.disclaimer.strings

public interface AiutaTryOnFitDisclaimerFeatureStrings {
    public val tryOnFitTitle: String
    public val tryOnFitDescription: String
    public val tryOnFitButtonClose: String

    public class Default : AiutaTryOnFitDisclaimerFeatureStrings {
        override val tryOnFitTitle: String = "Results may vary from real-life fit"
        override val tryOnFitDescription: String = "Virtual try-on is a visualization tool that shows how items might look and may not perfectly represent how the item will fit in reality"
        override val tryOnFitButtonClose: String = "Close"
    }
}

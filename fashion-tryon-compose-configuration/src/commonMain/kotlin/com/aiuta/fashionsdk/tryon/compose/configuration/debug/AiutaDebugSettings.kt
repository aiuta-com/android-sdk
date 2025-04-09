package com.aiuta.fashionsdk.tryon.compose.configuration.debug

public class AiutaDebugSettings(
    public val emptyStringsPolicy: AiutaValidationPolicy,
    public val listSizePolicy: AiutaValidationPolicy,
)

public val DefaultAiutaDebugSettings: AiutaDebugSettings by lazy {
    AiutaDebugSettings(
        emptyStringsPolicy = AiutaValidationPolicy.WARNING,
        listSizePolicy = AiutaValidationPolicy.WARNING,
    )
}

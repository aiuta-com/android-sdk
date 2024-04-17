package com.aiuta.fashionsdk.internal.analytic.model

public class SharedAnalyticEvent(
    public val name: String,
    public val params: Map<String, String?> = emptyMap(),
)

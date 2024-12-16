package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.meta

/**
 * Additional meta data from host about usage of SDK
 */
public class HostMetadata(
    public val mode: AiutaMode,
)

public val DefaultHostMetadata: HostMetadata by lazy {
    HostMetadata(
        mode = AiutaMode.FULL_SCREEN,
    )
}

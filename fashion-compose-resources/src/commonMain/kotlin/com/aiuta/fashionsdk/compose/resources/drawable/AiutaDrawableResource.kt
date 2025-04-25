package com.aiuta.fashionsdk.compose.resources.drawable

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.DrawableResource

@Immutable
public interface AiutaDrawableResource {
    public val resource: Any?
}

public class AiutaComposeDrawableResource(
    public override val resource: DrawableResource,
) : AiutaDrawableResource

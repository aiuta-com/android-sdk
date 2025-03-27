package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage

@Immutable
internal sealed interface LastSavedImageWrapper {
    class SavedUrlImage(
        val image: UrlImage,
    ) : LastSavedImageWrapper

    class SavedPlatformImage(
        val image: AiutaPlatformImage,
    ) : LastSavedImageWrapper
}

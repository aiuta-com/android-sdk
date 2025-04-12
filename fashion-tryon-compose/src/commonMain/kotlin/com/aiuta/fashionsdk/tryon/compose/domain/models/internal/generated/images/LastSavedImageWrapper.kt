package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.core.domain.models.file.AiutaPlatformFile

@Immutable
internal sealed interface LastSavedImageWrapper {
    class SavedUrlImage(
        val image: UrlImage,
    ) : LastSavedImageWrapper

    class SavedPlatformImage(
        val file: AiutaPlatformFile,
    ) : LastSavedImageWrapper
}

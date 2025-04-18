package com.aiuta.fashionsdk.tryon.compose.uikit.resources

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.aiuta.fashionsdk.compose.core.context.LocalAiutaPlatformContext
import com.aiuta.fashionsdk.io.AiutaPlatformFile
import com.aiuta.fashionsdk.io.readByteArray
import com.aiuta.fashionsdk.tryon.compose.uikit.internal.progress.ErrorProgress

@Immutable
internal sealed interface AiutaPlatformImageLoadingState {
    object Loading : AiutaPlatformImageLoadingState
    object Error : AiutaPlatformImageLoadingState
    class Success(val byteArray: ByteArray) : AiutaPlatformImageLoadingState
}

@Composable
public fun AiutaPlatformImage(
    platformFile: AiutaPlatformFile,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    val imageState = remember {
        mutableStateOf<AiutaPlatformImageLoadingState>(AiutaPlatformImageLoadingState.Loading)
    }
    val context = LocalAiutaPlatformContext.current

    LaunchedEffect(Unit) {
        imageState.value = try {
            AiutaPlatformImageLoadingState.Success(platformFile.readByteArray(context))
        } catch (e: Exception) {
            AiutaPlatformImageLoadingState.Error
        }
    }

    // Need box for more smooth transition
    Box(modifier = modifier) {
        when (val state = imageState.value) {
            is AiutaPlatformImageLoadingState.Loading -> Unit
            is AiutaPlatformImageLoadingState.Error -> ErrorProgress(modifier = modifier)
            is AiutaPlatformImageLoadingState.Success -> {
                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = state.byteArray,
                    contentScale = contentScale,
                    alignment = alignment,
                    alpha = alpha,
                    colorFilter = colorFilter,
                    contentDescription = contentDescription,
                )
            }
        }
    }
}

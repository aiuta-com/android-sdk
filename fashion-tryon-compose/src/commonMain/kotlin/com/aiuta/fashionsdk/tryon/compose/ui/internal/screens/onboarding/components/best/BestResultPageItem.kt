package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.best

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.molecules.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage

@Composable
internal fun BestResultPageItem(
    modifier: Modifier = Modifier,
    image: AiutaImage,
    isGoodImage: Boolean,
) {
    val theme = LocalTheme.current

    Box(
        modifier = modifier.clip(theme.shapes.onboardingImage),
    ) {
        AiutaImage(
            modifier = modifier.fillMaxSize(),
            image = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        AiutaIcon(
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .size(24.dp),
            icon =
                if (isGoodImage) {
                    theme.icons.checkCorrect24
                } else {
                    theme.icons.checkNotCorrect24
                },
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

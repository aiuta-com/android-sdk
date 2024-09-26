package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.best

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.icon.checkCorrect24
import com.aiuta.fashionsdk.compose.tokens.icon.checkNotCorrect24
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme

@Composable
internal fun BestResultPageItem(
    modifier: Modifier = Modifier,
    image: Int,
    isGoodImage: Boolean,
) {
    val theme = LocalTheme.current

    Box(
        modifier = modifier.clip(RoundedCornerShape(24.dp)),
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        Icon(
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .size(24.dp),
            painter =
                rememberAsyncImagePainter(
                    model =
                        if (isGoodImage) {
                            theme.icons.checkCorrect24
                        } else {
                            theme.icons.checkNotCorrect24
                        },
                ),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

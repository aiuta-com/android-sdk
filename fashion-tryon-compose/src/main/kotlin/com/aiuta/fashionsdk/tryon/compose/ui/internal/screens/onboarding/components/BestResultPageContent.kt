package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.BestResultPage

@Composable
internal fun BestResultPageContent(
    modifier: Modifier = Modifier,
    state: BestResultPage,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            state.goodImages.forEach { image ->
                BestResultPageItem(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    image = image,
                    isGoodImage = true,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            state.badImages.forEach { image ->
                BestResultPageItem(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    image = image,
                    isGoodImage = false,
                )
            }
        }
    }
}

@Composable
private fun BestResultPageItem(
    modifier: Modifier = Modifier,
    image: Int,
    isGoodImage: Boolean,
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(24.dp)),
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        Box(
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .size(24.dp)
                    .background(
                        color = if (isGoodImage) Color(0xFF00C35A) else Color(0xFFEF5754),
                        shape = CircleShape,
                    ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector =
                    ImageVector.vectorResource(
                        id = if (isGoodImage) FashionIcon.Check16 else FashionIcon.Cross36,
                    ),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

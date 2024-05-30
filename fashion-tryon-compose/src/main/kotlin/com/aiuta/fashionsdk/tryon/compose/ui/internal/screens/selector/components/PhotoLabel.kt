package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.solveStringResource
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources

@Composable
internal fun PhotoLabel(
    modifier: Modifier = Modifier,
    count: Int,
) {
    val stringResources = LocalAiutaTryOnStringResources.current

    Box(
        modifier =
            modifier
                .background(
                    color = Color.Black.copy(alpha = 0.58f),
                    shape = RoundedCornerShape(4.dp),
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 6.dp,
                ),
    ) {
        Text(
            text = stringResources.imageSelectorPhotos.solveStringResource(count),
            style = MaterialTheme.typography.subtitle2,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

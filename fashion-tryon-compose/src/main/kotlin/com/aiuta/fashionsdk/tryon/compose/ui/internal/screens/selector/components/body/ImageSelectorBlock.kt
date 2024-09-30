package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Composable
internal fun ImageSelectorBlock(
    modifier: Modifier = Modifier,
    uploadPhoto: () -> Unit,
) {
    val theme = LocalTheme.current

    Box(
        modifier =
            modifier
                .background(
                    color = theme.colors.neutral,
                    shape = theme.shapes.mainImage,
                ),
    ) {
        ImageSelectorPhoto(modifier = Modifier.fillMaxSize())

        Spacer(Modifier.height(26.dp))

        ImageSelectorBottom(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 38.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter),
            uploadPhoto = uploadPhoto,
        )
    }
}

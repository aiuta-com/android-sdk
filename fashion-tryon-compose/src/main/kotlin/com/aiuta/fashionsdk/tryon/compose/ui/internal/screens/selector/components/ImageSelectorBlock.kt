package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

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
                    color = theme.colors.gray1,
                    shape = RoundedCornerShape(24.dp),
                )
    ) {
        ImageSelectorPhoto(
            modifier = Modifier.fillMaxSize(),
        )

        Spacer(Modifier.height(26.dp))

        ImageSelectorBottom(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp)
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter),
            uploadPhoto = uploadPhoto,
        )
    }
}

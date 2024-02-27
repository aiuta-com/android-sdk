package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionColor

@Composable
internal fun ImageSelectorBlock(
    modifier: Modifier = Modifier,
    uploadPhoto: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .background(
                    color = FashionColor.White,
                    shape = RoundedCornerShape(24.dp),
                )
                .padding(horizontal = 38.dp)
                .padding(top = 38.dp, bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageSelectorPhoto(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
        )

        Spacer(Modifier.height(26.dp))

        ImageSelectorBottom(
            modifier = Modifier.fillMaxWidth(),
            uploadPhoto = uploadPhoto,
        )
    }
}

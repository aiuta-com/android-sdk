package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun ColumnScope.SheetDivider() {
    val theme = LocalTheme.current

    val dividerWidth = theme.bottomSheet.grabber.width
    val dividerTopPadding = theme.bottomSheet.grabber.topPadding

    Spacer(Modifier.height(dividerTopPadding))

    Divider(
        modifier =
        Modifier
            .width(dividerWidth)
            .height(3.dp)
            .clip(RoundedCornerShape(6.dp))
            .align(Alignment.CenterHorizontally),
        color = Color(0xFFC5C5C5),
    )
}

package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode

@Composable
internal fun SelectorCard(
    modifier: Modifier = Modifier,
    selectionMode: MutableState<SelectorMode>,
    isActionActive: Boolean,
    onSelectAll: () -> Unit,
    onDeselectAll: () -> Unit,
    onCancel: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Row(
        modifier =
            modifier
                .background(
                    color = theme.colors.primary,
                    shape = theme.shapes.buttonM,
                )
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                text = stringResources.historySelectorEnableButtonCancel,
                backgroundColor = theme.colors.onDark,
                textColor = theme.colors.primary,
            ) {
                onCancel()
            }

            AnimatedContent(
                targetState = selectionMode.value,
                transitionSpec = {
                    slideInVertically(
                        animationSpec = tween(durationMillis = 400),
                        initialOffsetY = { -it },
                    ) + fadeIn() togetherWith slideOutVertically(
                        animationSpec = tween(durationMillis = 400),
                        targetOffsetY = { it },
                    ) + fadeOut()
                },
                label = "selectable button",
            ) { state ->
                TextButton(
                    text =
                        if (state == SelectorMode.ALL_IS_SELECTED) {
                            stringResources.historySelectorEnableButtonUnselectAll
                        } else {
                            stringResources.historySelectorEnableButtonSelectAll
                        },
                    backgroundColor = Color.Transparent,
                    textColor = theme.colors.onDark,
                ) {
                    if (state == SelectorMode.ALL_IS_SELECTED) {
                        onDeselectAll()
                    } else {
                        onSelectAll()
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            SmallIconButton(
                icon = theme.icons.trash24,
                isActive = isActionActive,
            ) {
                onDelete()
            }

            Spacer(Modifier.width(16.dp))

            SmallIconButton(
                icon = theme.icons.share24,
                isActive = isActionActive,
            ) {
                onShare()
            }
        }
    }
}

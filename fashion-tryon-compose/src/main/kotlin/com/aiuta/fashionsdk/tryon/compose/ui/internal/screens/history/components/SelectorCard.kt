package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode

@Composable
internal fun SelectorCard(
    modifier: Modifier = Modifier,
    selectionMode: MutableState<SelectorMode>,
    isActionActive: Boolean,
    onSelectAll: () -> Unit,
    onDeselectAll: () -> Unit,
    onStartSelectionMode: () -> Unit,
    onCancel: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit,
) {
    val transition =
        updateTransition(
            targetState = selectionMode.value == SelectorMode.DISABLED,
            label = "selectorState",
        )

    Row(
        modifier =
            modifier
                .background(
                    color = FashionColor.Black,
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        transition.AnimatedContent { isDisables ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isDisables) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.history_selector_disabled_text),
                        style = MaterialTheme.typography.body2,
                        color = FashionColor.White,
                    )

                    Spacer(Modifier.width(12.dp))

                    TextButton(text = stringResource(R.string.history_selector_disabled_button)) {
                        onStartSelectionMode()
                    }
                } else {
                    SmallIconButton(
                        iconRes = FashionIcon.Trash36,
                        isActive = isActionActive,
                    ) {
                        onDelete()
                    }

                    Spacer(Modifier.width(16.dp))

                    SmallIconButton(
                        iconRes = FashionIcon.Share36,
                        isActive = isActionActive,
                    ) {
                        onShare()
                    }

                    Spacer(Modifier.weight(1f))

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
                                    stringResource(
                                        R.string.history_selector_enable_button_unselect_all,
                                    )
                                } else {
                                    stringResource(
                                        R.string.history_selector_enable_button_select_all,
                                    )
                                },
                            backgroundColor = Color.Transparent,
                            textColor = FashionColor.White,
                        ) {
                            if (state == SelectorMode.ALL_IS_SELECTED) {
                                onDeselectAll()
                            } else {
                                onSelectAll()
                            }
                        }
                    }

                    TextButton(
                        text = stringResource(R.string.history_selector_enable_button_cancel),
                    ) {
                        onCancel()
                    }
                }
            }
        }
    }
}

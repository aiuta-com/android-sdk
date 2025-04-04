package com.aiuta.fashionsdk.tryon.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashion_tryon_icons.generated.resources.Res
import com.aiuta.fashion_tryon_icons.generated.resources.ic_back_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_check_16
import com.aiuta.fashion_tryon_icons.generated.resources.ic_close_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_error_36
import com.aiuta.fashion_tryon_icons.generated.resources.ic_image_error_36
import com.aiuta.fashion_tryon_icons.generated.resources.ic_trash_24
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcon
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcons

/**
 * Default implementation of [AiutaIcons].
 */
@Deprecated("Migrate to defaults")
public fun defaultAiutaIcons(): AiutaIcons = AiutaResourceIcons(
    error36 = AiutaResourceIcon(Res.drawable.ic_error_36),
    imageError36 = AiutaResourceIcon(Res.drawable.ic_image_error_36),
    back24 = AiutaResourceIcon(Res.drawable.ic_back_24),
    close24 = AiutaResourceIcon(Res.drawable.ic_close_24),
    trash24 = AiutaResourceIcon(Res.drawable.ic_trash_24),
    check20 = AiutaResourceIcon(Res.drawable.ic_check_16),
)

@Deprecated("Migrate to defaults")
@Composable
public fun rememberDefaultAiutaIcons(): AiutaIcons = remember { defaultAiutaIcons() }

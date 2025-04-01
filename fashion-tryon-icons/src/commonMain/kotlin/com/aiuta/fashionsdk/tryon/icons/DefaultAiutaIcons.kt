package com.aiuta.fashionsdk.tryon.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashion_tryon_icons.generated.resources.Res
import com.aiuta.fashion_tryon_icons.generated.resources.ic_arrow_16
import com.aiuta.fashion_tryon_icons.generated.resources.ic_back_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_camera_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_check_16
import com.aiuta.fashion_tryon_icons.generated.resources.ic_check_correct_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_check_not_correct_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_close_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_dislike_36
import com.aiuta.fashion_tryon_icons.generated.resources.ic_error_36
import com.aiuta.fashion_tryon_icons.generated.resources.ic_history_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_image_error_36
import com.aiuta.fashion_tryon_icons.generated.resources.ic_info_20
import com.aiuta.fashion_tryon_icons.generated.resources.ic_like_36
import com.aiuta.fashion_tryon_icons.generated.resources.ic_loader_14
import com.aiuta.fashion_tryon_icons.generated.resources.ic_lock_16
import com.aiuta.fashion_tryon_icons.generated.resources.ic_magic_20
import com.aiuta.fashion_tryon_icons.generated.resources.ic_photo_library_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_recent_100
import com.aiuta.fashion_tryon_icons.generated.resources.ic_select_model_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_share_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_take_photo_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_trash_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_welcome_screen_82
import com.aiuta.fashion_tryon_icons.generated.resources.ic_wishlist_24
import com.aiuta.fashion_tryon_icons.generated.resources.ic_wishlist_fill_24
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcon
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcons

/**
 * Default implementation of [AiutaIcons].
 */
public fun defaultAiutaIcons(): AiutaIcons {
    return AiutaResourceIcons(
        recent100 = AiutaResourceIcon(Res.drawable.ic_recent_100),
        welcomeScreen82 = AiutaResourceIcon(Res.drawable.ic_welcome_screen_82),
        error36 = AiutaResourceIcon(Res.drawable.ic_error_36),
        like36 = AiutaResourceIcon(Res.drawable.ic_like_36),
        dislike36 = AiutaResourceIcon(Res.drawable.ic_dislike_36),
        imageError36 = AiutaResourceIcon(Res.drawable.ic_image_error_36),
        back24 = AiutaResourceIcon(Res.drawable.ic_back_24),
        camera24 = AiutaResourceIcon(Res.drawable.ic_camera_24),
        checkCorrect24 = AiutaResourceIcon(Res.drawable.ic_check_correct_24),
        checkNotCorrect24 =
            AiutaResourceIcon(
                Res.drawable.ic_check_not_correct_24,
            ),
        close24 = AiutaResourceIcon(Res.drawable.ic_close_24),
        trash24 = AiutaResourceIcon(Res.drawable.ic_trash_24),
        takePhoto24 = AiutaResourceIcon(Res.drawable.ic_take_photo_24),
        history24 = AiutaResourceIcon(Res.drawable.ic_history_24),
        magic20 = AiutaResourceIcon(Res.drawable.ic_magic_20),
        photoLibrary24 = AiutaResourceIcon(Res.drawable.ic_photo_library_24),
        selectModel24 = AiutaResourceIcon(Res.drawable.ic_select_model_24),
        share24 = AiutaResourceIcon(Res.drawable.ic_share_24),
        wishlist24 = AiutaResourceIcon(Res.drawable.ic_wishlist_24),
        wishlistFill24 = AiutaResourceIcon(Res.drawable.ic_wishlist_fill_24),
        info20 = AiutaResourceIcon(Res.drawable.ic_info_20),
        check20 = AiutaResourceIcon(Res.drawable.ic_check_16),
        lock16 = AiutaResourceIcon(Res.drawable.ic_lock_16),
        arrow16 = AiutaResourceIcon(Res.drawable.ic_arrow_16),
        loading14 = AiutaResourceIcon(Res.drawable.ic_loader_14),
    )
}

@Composable
public fun rememberDefaultAiutaIcons(): AiutaIcons {
    return remember { defaultAiutaIcons() }
}

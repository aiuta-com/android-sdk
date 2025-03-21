package com.aiuta.fashionsdk.tryon.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaResourceIcons

/**
 * Default implementation of [AiutaIcons].
 */
public fun defaultAiutaIcons(): AiutaIcons {
    return AiutaResourceIcons(
        recent100 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_recent_100),
        welcomeScreen82 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_welcome_screen_82),
        error36 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_error_36),
        like36 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_like_36),
        dislike36 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_dislike_36),
        imageError36 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_image_error_36),
        back24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_back_24),
        camera24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_camera_24),
        checkCorrect24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_check_correct_24),
        checkNotCorrect24 =
            AiutaResourceIcons.AiutaResourceIcon(
                R.drawable.ic_check_not_correct_24,
            ),
        close24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_close_24),
        trash24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_trash_24),
        takePhoto24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_take_photo_24),
        history24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_history_24),
        magic20 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_magic_20),
        photoLibrary24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_photo_library_24),
        selectModel24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_select_model_24),
        share24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_share_24),
        wishlist24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_wishlist_24),
        wishlistFill24 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_wishlist_fill_24),
        info20 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_info_20),
        check20 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_check_16),
        lock16 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_lock_16),
        arrow16 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_arrow_16),
        loading14 = AiutaResourceIcons.AiutaResourceIcon(R.drawable.ic_loader_14),
    )
}

@Composable
public fun rememberDefaultAiutaIcons(): AiutaIcons {
    return remember { defaultAiutaIcons() }
}

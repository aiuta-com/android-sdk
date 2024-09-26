package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.R
import java.util.UUID

internal object TryOnPage : OnboardingState, Iterable<TryOnPage.InternalPage> {
    @Immutable
    data class InternalPage(
        @DrawableRes
        val mainImage: Int,
        @DrawableRes
        val itemImage: Int,
    ) {
        internal val uniqueGeneratedId: String = UUID.randomUUID().toString()
    }

    val INTERNAL_PAGES by lazy {
        listOf(
            InternalPage(
                mainImage = R.drawable.onboarding_main_1,
                itemImage = R.drawable.onboarding_item_1,
            ),
            InternalPage(
                mainImage = R.drawable.onboarding_main_2,
                itemImage = R.drawable.onboarding_item_2,
            ),
            InternalPage(
                mainImage = R.drawable.onboarding_main_3,
                itemImage = R.drawable.onboarding_item_3,
            ),
        )
    }

    override fun iterator(): Iterator<InternalPage> {
        return INTERNAL_PAGES.iterator()
    }
}

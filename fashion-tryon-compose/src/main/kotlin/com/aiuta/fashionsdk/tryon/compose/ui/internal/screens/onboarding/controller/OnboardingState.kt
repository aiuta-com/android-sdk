package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.R
import java.util.UUID

@Immutable
internal sealed interface OnboardingState {
    val topic: String

    val subtopic: String
}

internal class TryOnPage(
    override val topic: String,
    override val subtopic: String,
) : OnboardingState, Iterable<TryOnPage.InternalPage> {
    @Immutable
    data class InternalPage(
        @DrawableRes
        val mainImage: Int,
        @DrawableRes
        val itemImage: Int,
    ) {
        internal val uniqueGeneratedId: String = UUID.randomUUID().toString()
    }

    override fun iterator(): Iterator<InternalPage> {
        return INTERNAL_PAGES.iterator()
    }

    companion object {
        val INTERNAL_PAGES =
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
}

internal class BestResultPage(
    override val topic: String,
    override val subtopic: String,
) : OnboardingState {
    val goodImages: List<Int> =
        listOf(
            R.drawable.onboarding_good_image_1,
            R.drawable.onboarding_good_image_2,
        )

    val badImages: List<Int> =
        listOf(
            R.drawable.onboarding_bad_image_1,
            R.drawable.onboarding_bad_image_2,
        )
}

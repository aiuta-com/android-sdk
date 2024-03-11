package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.R
import java.util.UUID

@Immutable
internal sealed interface OnboardingState {
    @get:StringRes
    val topic: Int

    @get:StringRes
    val subtopic: Int
}

internal object TryOnPage : OnboardingState, Iterable<TryOnPage.InternalPage> {
    override val topic: Int = R.string.onboarding_page_tryon_topic
    override val subtopic: Int = R.string.onboarding_page_tryon_subtopic

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
}

internal object BestResultPage : OnboardingState {
    override val topic: Int = R.string.onboarding_page_best_result_topic
    override val subtopic: Int = R.string.onboarding_page_best_result_subtopic

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

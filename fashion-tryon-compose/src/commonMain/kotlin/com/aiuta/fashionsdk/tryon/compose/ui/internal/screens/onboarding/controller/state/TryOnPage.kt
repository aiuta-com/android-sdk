package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPage
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class TryOnPage(
    tryOnPageFeature: AiutaOnboardingTryOnPage,
) : OnboardingState, Iterable<TryOnPage.InternalPage> {
    @Immutable
    data class InternalPage(
        val mainImage: AiutaImage,
        val itemImage: AiutaImage,
    ) {
        @OptIn(ExperimentalUuidApi::class)
        internal val uniqueGeneratedId: String = Uuid.random().toString()
    }

    val internalPages by lazy {
        tryOnPageFeature.images.onboardingTryOnItems.map { item ->
            InternalPage(
                mainImage = item.itemPhoto,
                itemImage = item.itemPreview,
            )
        }
    }

    override fun iterator(): Iterator<InternalPage> {
        return internalPages.iterator()
    }

    override val pageTitle: String? = tryOnPageFeature.strings.onboardingTryOnPageTitle

    override fun pageSize(): Int {
        return internalPages.size
    }

    companion object {
        const val INTERNAL_PAGES_SIZE = 3
        const val INTERNAL_PAGES_LAST_INDEX = INTERNAL_PAGES_SIZE - 1
    }
}

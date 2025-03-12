package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImages
import java.util.UUID

internal class TryOnPage(
    aiutaImages: AiutaImages,
) : OnboardingState, Iterable<TryOnPage.InternalPage> {
    @Immutable
    data class InternalPage(
        val mainImage: AiutaImage,
        val itemImage: AiutaImage,
    ) {
        internal val uniqueGeneratedId: String = UUID.randomUUID().toString()
    }

    val internalPages by lazy {
        listOf(
            InternalPage(
                mainImage = aiutaImages.onboardingImages.onboardingTryOnMainImage1,
                itemImage = aiutaImages.onboardingImages.onboardingTryOnItemImage1,
            ),
            InternalPage(
                mainImage = aiutaImages.onboardingImages.onboardingTryOnMainImage2,
                itemImage = aiutaImages.onboardingImages.onboardingTryOnItemImage2,
            ),
            InternalPage(
                mainImage = aiutaImages.onboardingImages.onboardingTryOnMainImage3,
                itemImage = aiutaImages.onboardingImages.onboardingTryOnItemImage3,
            ),
        )
    }

    override fun iterator(): Iterator<InternalPage> {
        return internalPages.iterator()
    }

    override fun pageSize(): Int {
        return internalPages.size
    }

    companion object {
        const val INTERNAL_PAGES_SIZE = 3
        const val INTERNAL_PAGES_LAST_INDEX = INTERNAL_PAGES_SIZE - 1
    }
}

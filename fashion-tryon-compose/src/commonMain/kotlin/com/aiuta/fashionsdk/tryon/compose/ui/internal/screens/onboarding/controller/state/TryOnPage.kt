package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource
import com.aiuta.fashionsdk.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPageFeature
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class TryOnPage(
    tryOnPageFeature: AiutaOnboardingTryOnPageFeature,
) : OnboardingState,
    Iterable<TryOnPage.InternalPage> {
    @Immutable
    data class InternalPage(
        val mainImage: AiutaDrawableResource,
        val itemImage: AiutaDrawableResource,
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

    override fun iterator(): Iterator<InternalPage> = internalPages.iterator()

    override val pageTitle: String? = tryOnPageFeature.strings.onboardingTryOnPageTitle

    override fun pageSize(): Int = internalPages.size

    companion object {
        const val INTERNAL_PAGES_SIZE = 3
        const val INTERNAL_PAGES_LAST_INDEX = INTERNAL_PAGES_SIZE - 1
    }
}

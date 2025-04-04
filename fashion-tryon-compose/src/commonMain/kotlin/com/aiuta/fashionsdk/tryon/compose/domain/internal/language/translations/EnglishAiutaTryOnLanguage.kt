package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class EnglishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "History"
    override val appBarSelect: String = "Select"

    override val modelSelectorErrorEmptyModelsList: String = "The models list is empty"

    // History
    override val historySelectorEnableButtonSelectAll: String = "Select all"
    override val historySelectorEnableButtonUnselectAll: String = "Unselect all"
    override val historyEmptyDescription: String =
        "Once you try on first item your try-on history would be stored here"

    // General
    override val addToWish: String = "Wishlist"
    override val cancel: String = "Cancel"
    override val tryAgain: String = "Try again"
    override val defaultErrorMessage: String = "Something went wrong. Please try again later"
}

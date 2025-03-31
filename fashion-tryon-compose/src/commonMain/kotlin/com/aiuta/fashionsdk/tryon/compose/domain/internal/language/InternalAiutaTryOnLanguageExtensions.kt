package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal fun InternalAiutaTryOnLanguage.isCustomLanguage(): Boolean {
    contract {
        returns(true) implies (this@isCustomLanguage is CustomLanguage)
    }

    return this is CustomLanguage
}

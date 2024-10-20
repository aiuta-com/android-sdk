package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.domain.models.CustomLanguage

internal fun InternalAiutaTryOnLanguage.isCustomLanguage(): Boolean {
    return this is CustomLanguage
}

package com.aiuta.fashionsdk.network.paging.utils

import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import io.ktor.http.URLBuilder

/**
 * Extension for [URLBuilder] for save append pagination аffset.
 * If [paginationOffset] is null - will NOT apply it to [URLBuilder].
 */
public fun URLBuilder.saveAppend(paginationOffset: PaginationOffset?) {
    paginationOffset?.let {
        parameters.append(paginationOffset.direction.httpQueryKey, paginationOffset.key)
    }
}

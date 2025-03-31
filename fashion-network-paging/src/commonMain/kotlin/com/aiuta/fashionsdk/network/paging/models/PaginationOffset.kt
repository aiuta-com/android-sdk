package com.aiuta.fashionsdk.network.paging.models

/**
 * Class for wrapping [direction] of new paging request
 *
 * @param direction Direction of new paging request
 * @param key One of [PageContainer.beforeKey] or [PageContainer.afterKey]
 *
 * @see PageContainer
 * @see PaginationDirection
 */
public data class PaginationOffset(
    val direction: PaginationDirection,
    val key: String,
)

/**
 * Encapsulate possible directions for pagination, corresponding
 * to [PageContainer.beforeKey] or [PageContainer.afterKey]
 */
public enum class PaginationDirection(
    public val httpQueryKey: String,
) {
    BEFORE("before"),
    AFTER("after"),
}

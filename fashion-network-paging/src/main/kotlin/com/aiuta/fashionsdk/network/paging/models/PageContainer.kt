package com.aiuta.fashionsdk.network.paging.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Base container for paging response
 */
@Serializable
public data class PageContainer<T>(
    /**
     * Result list of new page
     */
    @SerialName("result")
    val result: List<T>,
    /**
     * Key of first element in page.
     * Use it, to get previous page
     */
    @SerialName("before")
    var beforeKey: String,
    /**
     * Key of last element in page.
     * Use it, to get next page
     */
    @SerialName("after")
    var afterKey: String,
    /**
     * List of possible errors from backend
     */
    @SerialName("errors")
    val errors: List<String>,
)

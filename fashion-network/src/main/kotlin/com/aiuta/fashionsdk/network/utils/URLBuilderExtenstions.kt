package com.aiuta.fashionsdk.network.utils

import io.ktor.http.URLBuilder

private const val DEFAULT_LIMIT_KEY = "limit"

/**
 * Extension for [URLBuilder] for save append null possible parameters.
 * If [parameter] is null - will NOT apply it to [URLBuilder].
 */
public fun URLBuilder.saveAppend(
    key: String,
    parameter: String?,
) {
    parameter?.let {
        parameters.append(key, it)
    }
}

/**
 * Extension for [URLBuilder] for save append null possible parameters.
 * If [parameter] is null - will NOT apply it to [URLBuilder].
 */
public fun URLBuilder.saveAppend(
    key: String,
    parameter: Int?,
) {
    parameter?.let {
        parameters.append(key, it.toString())
    }
}

/**
 * Extension for [URLBuilder] for save append list of parameters.
 * If [listParameter] is empty - will NOT apply it to [URLBuilder].
 */
public fun URLBuilder.saveAppend(
    key: String,
    listParameter: List<String>,
) {
    if (listParameter.isNotEmpty()) {
        parameters.appendAll(key, listParameter)
    }
}

/**
 * Extension for [URLBuilder] for save append limit in paging request.
 * If [limit] is null - will NOT apply it to [URLBuilder].
 */
public fun URLBuilder.saveAppendLimit(limit: Int?) {
    saveAppend(
        key = DEFAULT_LIMIT_KEY,
        parameter = limit,
    )
}

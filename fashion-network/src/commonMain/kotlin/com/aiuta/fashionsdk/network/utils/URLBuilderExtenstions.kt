package com.aiuta.fashionsdk.network.utils

import io.ktor.http.URLBuilder

private const val DEFAULT_LIMIT_KEY = "limit"

/**
 * Extension for [URLBuilder] that safely appends a nullable string parameter.
 * If the parameter is null, it will not be added to the URL.
 *
 * @param key The parameter key to append
 * @param parameter The nullable string parameter value
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
 * Extension for [URLBuilder] that safely appends a nullable integer parameter.
 * If the parameter is null, it will not be added to the URL.
 *
 * @param key The parameter key to append
 * @param parameter The nullable integer parameter value
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
 * Extension for [URLBuilder] that safely appends a list of string parameters.
 * If the list is empty, no parameters will be added to the URL.
 *
 * @param key The parameter key to append
 * @param listParameter The list of string parameter values
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
 * Extension for [URLBuilder] that safely appends a limit parameter for paging requests.
 * If the limit is null, it will not be added to the URL.
 *
 * @param limit The nullable integer limit value
 */
public fun URLBuilder.saveAppendLimit(limit: Int?) {
    saveAppend(
        key = DEFAULT_LIMIT_KEY,
        parameter = limit,
    )
}

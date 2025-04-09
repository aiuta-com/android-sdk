package com.aiuta.fashionsdk.network.exceptions

/**
 * Exception for backend not success response with [errorCode]
 */
public class FashionIOException(
    public val errorCode: Int,
    public val errorMessages: List<String>,
    public val requestId: String,
    public val date: String,
) : Exception(
    "Backed error. Code: $errorCode, requestId: $requestId, errorMessages - $errorMessages",
)

package com.aiuta.fashionsdk.network.exceptions

/**
 * Exception thrown when a backend request returns a non-success response.
 * This exception contains detailed information about the error, including the error code,
 * error messages, request ID, and timestamp.
 *
 * @property errorCode The HTTP status code or custom error code returned by the backend
 * @property errorMessages List of error messages describing what went wrong
 * @property requestId Unique identifier for the failed request
 * @property date Timestamp of when the error occurred
 */
public class FashionIOException(
    public val errorCode: Int,
    public val errorMessages: List<String>,
    public val requestId: String,
    public val date: String,
) : Exception(
    "Backed error. Code: $errorCode, requestId: $requestId, errorMessages - $errorMessages",
)

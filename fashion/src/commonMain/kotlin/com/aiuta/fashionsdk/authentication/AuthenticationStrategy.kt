package com.aiuta.fashionsdk.authentication

/**
 * `AuthenticationStrategy` is a sealed interface that defines the contract for different
 * authentication strategies used within the Aiuta SDK.
 *
 * Implementations of this interface should provide the necessary logic to authenticate
 * requests made by the SDK.
 *
 * Example implementations include:
 * - [ApiKeyAuthenticationStrategy]: Uses an API key for authentication.
 * - [JWTAuthenticationStrategy]: Uses JWT tokens for authentication.
 */
public sealed interface AuthenticationStrategy

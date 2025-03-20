package com.aiuta.fashionsdk.authentication

/**
 * `ApiKeyAuthenticationStrategy` is a class that implements the [AuthenticationStrategy] interface.
 * It uses an API key for authenticating requests made by the Aiuta SDK.
 *
 * @property apiKey The API key used for authentication. It must be a non-empty string.
 *
 * @throws IllegalArgumentException if the provided API key is empty.
 */
public class ApiKeyAuthenticationStrategy(
    public val apiKey: String,
) : AuthenticationStrategy {
    init {
        check(
            value = apiKey.isNotEmpty(),
            lazyMessage = {
                "ApiKeyAuthenticationStrategy: apiKey is empty, please use not empty value for it"
            },
        )
    }
}

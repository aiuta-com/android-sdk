package com.aiuta.fashionsdk.authentication

// TODO Docs
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

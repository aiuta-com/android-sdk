package com.aiuta.fashionsdk.authentication

/**
 * `JWTAuthenticationStrategy` is a class that implements the [AuthenticationStrategy] interface.
 * It uses JWT tokens for authenticating requests made by the Aiuta SDK.
 *
 * @property getJWT A suspend function that retrieves a JWT token. The function takes an optional
 * parameter `params` and returns a JWT token as a String.
 */
public class JWTAuthenticationStrategy(
    public val getJWT: suspend (params: String?) -> String,
    override val subscriptionId: String,
) : AuthenticationStrategy

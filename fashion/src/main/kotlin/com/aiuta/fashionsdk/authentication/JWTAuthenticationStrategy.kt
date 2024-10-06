package com.aiuta.fashionsdk.authentication

// TODO Auth
public class JWTAuthenticationStrategy(
    public val getJWT: suspend (params: String?) -> String,
) : AuthenticationStrategy

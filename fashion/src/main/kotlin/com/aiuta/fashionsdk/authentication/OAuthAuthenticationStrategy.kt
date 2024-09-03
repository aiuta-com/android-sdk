package com.aiuta.fashionsdk.authentication

// TODO Docs
public class OAuthAuthenticationStrategy(
    public val clientId: String,
    public val getAccessToken: suspend () -> String,
) : AuthenticationStrategy

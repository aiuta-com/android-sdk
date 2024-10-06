package com.aiuta.fashionsdk.authentication

// TODO Docs
public class OAuthAuthenticationStrategy(
    public val getToken: suspend () -> String,
) : AuthenticationStrategy

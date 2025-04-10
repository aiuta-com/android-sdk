package com.aiuta.fashionsdk.context

public actual abstract class AiutaPlatformContext private constructor() {
    public companion object {
        public val INSTANCE: AiutaPlatformContext = object : AiutaPlatformContext() {}
    }
}

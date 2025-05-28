package com.aiuta.fashionsdk.context

/**
 * Non-Android implementation of [AiutaPlatformContext].
 *
 * For platforms other than Android (such as iOS, Desktop, etc.), this provides
 * a minimal implementation that doesn't require platform-specific context.
 *
 * @property INSTANCE Singleton instance for platforms that don't require context
 */
public actual abstract class AiutaPlatformContext private constructor() {
    public companion object {
        /**
         * Singleton instance of [AiutaPlatformContext] for non-Android platforms.
         *
         * This instance can be used when platform-specific context is not required
         * or when the platform doesn't have an equivalent to Android's Context.
         */
        public val INSTANCE: AiutaPlatformContext = object : AiutaPlatformContext() {}
    }
}

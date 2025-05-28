package com.aiuta.fashionsdk.context

/**
 * Platform-specific context abstraction for the Aiuta SDK.
 *
 * This abstract class provides a platform-agnostic way to handle platform-specific
 * context requirements across different platforms:
 *
 * - **Android**: Implemented as a typealias to `android.content.Context`
 * - **Other platforms**: Provides a singleton instance for platforms that don't require context
 *
 * The context is used internally by the SDK for platform-specific operations such as
 * file access, network configuration, and resource management.
 */
public expect abstract class AiutaPlatformContext

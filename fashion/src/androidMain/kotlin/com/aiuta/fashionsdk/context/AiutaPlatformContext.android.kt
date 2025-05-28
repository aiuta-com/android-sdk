package com.aiuta.fashionsdk.context

/**
 * Android implementation of [AiutaPlatformContext].
 * 
 * On Android, this is implemented as a typealias to `android.content.Context`,
 * allowing the SDK to access Android-specific functionality such as:
 * - Application resources
 * - File system access
 * - Network configuration
 * - System services
 */
public actual typealias AiutaPlatformContext = android.content.Context

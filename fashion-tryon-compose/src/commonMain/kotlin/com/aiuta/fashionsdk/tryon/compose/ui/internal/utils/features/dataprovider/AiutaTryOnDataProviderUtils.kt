package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.dataprovider

internal fun <R> Function0<R>?.safeInvoke(): Result<R>? = this?.let { runCatching { this() } }

internal fun <P1, R> Function1<P1, R>?.safeInvoke(param1: P1): Result<R>? = this?.let { runCatching { this(param1) } }

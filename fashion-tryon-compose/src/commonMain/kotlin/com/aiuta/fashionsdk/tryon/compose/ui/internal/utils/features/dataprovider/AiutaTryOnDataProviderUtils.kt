package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.dataprovider

internal fun <R> Function0<R>?.safeInvoke(): Result<R>? = this?.let { runCatching { this() } }
internal suspend fun <R> (suspend() -> R)?.safeSuspendInvoke(): Result<R>? = this?.let { runCatching { it() } }

internal fun <P1, R> Function1<P1, R>?.safeInvoke(param1: P1): Result<R>? = this?.let { runCatching { this(param1) } }
internal suspend fun <P1, R> (suspend(P1) -> R)?.safeSuspendInvoke(param1: P1): Result<R>? = this?.let { runCatching { it(param1) } }

internal fun <P1, P2, R> Function2<P1, P2, R>?.safeInvoke(param1: P1, param2: P2): Result<R>? = this?.let { runCatching { this(param1, param2) } }
internal suspend fun <P1, P2, R> (suspend(P1, P2) -> R)?.safeSuspendInvoke(param1: P1, param2: P2): Result<R>? = this?.let { runCatching { it(param1, param2) } }

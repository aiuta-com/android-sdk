package com.aiuta.fashionsdk.annotations

/**
 * DSL marker annotation for Aiuta SDK builder classes.
 *
 * This annotation is used to create type-safe builders and prevent
 * implicit receivers from being used in nested DSL scopes, ensuring
 * a clean and predictable DSL experience.
 *
 * Classes annotated with [AiutaDsl] will have restricted scope access
 * in nested lambda expressions, preventing accidental access to outer
 * scope receivers.
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
public annotation class AiutaDsl

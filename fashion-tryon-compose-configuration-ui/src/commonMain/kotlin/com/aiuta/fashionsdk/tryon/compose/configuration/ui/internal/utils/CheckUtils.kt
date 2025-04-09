package com.aiuta.fashionsdk.tryon.compose.configuration.ui.internal.utils

internal fun <T> T?.checkNotNullWithDescription(
    parentClass: String,
    property: String,
): T = checkNotNull(
    value = this,
    lazyMessage = {
        propertyIsNull(
            parentClass = parentClass,
            property = property,
        )
    },
)

internal fun propertyIsNull(
    parentClass: String,
    property: String,
): String = """
        $parentClass: $property is null, therefore cannot init $parentClass.
        Please, call initialize of $property in $parentClass builder dsl
""".trimIndent()

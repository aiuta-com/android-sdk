# Aiuta Try On flow

In this tutorial, you will learn how to use [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) power with
[Jetpack Compose Framework](https://developer.android.com/jetpack/compose)


## Prerequisites

Before starting this tutorial:
- [Initialize Aiuta Try On Flows](Get-started-with-Flows.md)


## Use Aiuta Digital Try On in Compose

Now you can use the prepared UI to try on the SKU.
To do this, call the following in the appropriate place:
```kotlin
    AiutaTryOnFlow(
        modifier = ...,
        aiuta = { ... },
        aiutaTryOn = { ... },
        aiutaTryOnListeners = { ... },
        theme = { ... },
        skuForGeneration = { ... },
    )
```

Where:
- `aiuta` - is a instance of `Aiuta` for initializing of internal flow (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion/src/main/kotlin/com/aiuta/fashionsdk/Aiuta.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion/com.aiuta.fashionsdk/-aiuta/index.html)
)
- `aiutaTryOn` - is a instance of `AiutaTryOn` for using all logic of [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-core/src/main/kotlin/com/aiuta/fashionsdk/tryon/core/AiutaTryOn.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-core/com.aiuta.fashionsdk.tryon.core/-aiuta-try-on/index.html)
)
- `aiutaTryOnListeners` - is a instance of `AiutaTryOnListeners` for observing user interaction inside flow (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/AiutaTryOnListeners.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-aiuta-try-on-listeners/index.html)
)
- `theme` - is a optional theme decoration for `AiutaTryOnFlow` (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/AiutaTryOnTheme.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-aiuta-try-on-theme/index.html)
)
- `skuForGeneration` - is SKU item for fitting on picked photo (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/SKUItem.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-s-k-u-item/index.html)
)

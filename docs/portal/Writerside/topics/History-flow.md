# History flow

In this tutorial, you will learn how to integrate History flow with results of generation 
created by [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on)


## Prerequisites

Before starting this tutorial:
- [Initialize Aiuta Try On Flows](Get-started-with-Flows.md)


## Use History Flow in Compose

You also have the ability to integrate user generation 
history into your application by invoking the following:
```kotlin
    HistoryFlow(
        modifier = ...,
        aiuta = { ... },
        aiutaTryOn = { ... },
        aiutaTryOnListeners = { ... },
        theme: (() -> AiutaTryOnTheme)? = { ... },
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
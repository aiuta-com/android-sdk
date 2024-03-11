# Compose flow

In this tutorial, you will learn how to use [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) power with
[Jetpack Compose Framework](https://developer.android.com/jetpack/compose)


## Prerequisites

Before starting this tutorial:
- [Initialize Aiuta Try On](Getting-started-with-Fashion-Try-On.md)


## Add dependencies

Let's add dependencies to use [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on)
with [Jetpack Compose](https://developer.android.com/jetpack/compose)

<procedure collapsible="false">
    <step>Firstly, add base dependencies, as described in
    <a href="Getting-started-with-Fashion.md" anchor="add-dependencies">Getting started with Aiuta</a>
    and
    <a href="Getting-started-with-Fashion-Try-On.md" anchor="add-dependencies">Getting started with Aiuta</a>
    guides
    </step>
    <step>Add try on compose dependency to have access to <code>AiutaTryOnFlow</code>
        <code-block lang="kotlin">
            dependencies {
                val fashionVersion: String = "%latest_fashion_version%"
                implementation("com.aiuta:fashionsdk:$fashionVersion")
                implementation("com.aiuta:fashionsdk-tryon-core:$fashionVersion")
                implementation("com.aiuta:fashionsdk-tryon-compose:$fashionVersion")
            }
        </code-block>
    </step>
</procedure>


## Use Aiuta Digital Try On in Compose

Now you can use the prepared UI to try on the SKU.
To do this, call the following in the appropriate place:
```kotlin
    AiutaTryOnFlow(
        modifier = ...,
        aiutaTryOn = { ... },
        aiutaTryOnListeners = { ... },
        theme = { ... },
        skuForGeneration = { ... },
    )
```

Where:
- `aiutaTryOnListeners` - is a instance of `AiutaTryOnListeners` for observing user interaction inside flow (
[code](https://github.com/aiuta-com/android-sdk/blob/main/aiuta-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/AiutaTryOnListeners.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/aiuta-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-aiuta-try-on-listeners/index.html)
)
- `aiutaTryOn` - is a instance of `AiutaTryOn` for using all logic of [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) (
[code](https://github.com/aiuta-com/android-sdk/blob/main/aiuta-tryon-core/src/main/kotlin/com/aiuta/fashionsdk/tryon/core/AiutaTryOn.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/aiuta-tryon-core/com.aiuta.fashionsdk.tryon.core/-aiuta-try-on/index.html)
)
- `skuForGeneration` - is SKU item for fitting on picked photo (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/SKUItem.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-s-k-u-item/index.html)
)
- `theme` - is a optional theme decoration for `AiutaTryOnFlow` (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/AiutaTryOnTheme.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-aiuta-try-on-theme/index.html)
)

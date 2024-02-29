# Compose flow

In this tutorial, you will learn how to use [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) power with
[Jetpack Compose Framework](https://developer.android.com/jetpack/compose)


## Prerequisites

Before starting this tutorial:
- [Initialize Fashion Try On](Getting-started-with-Fashion-Try-On.md)


## Add dependencies

Let's add dependencies to use [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) 
with [Jetpack Compose](https://developer.android.com/jetpack/compose)

<procedure collapsible="false">
    <step>Firstly, add base dependencies, as described in 
    <a href="Getting-started-with-Fashion.md" anchor="add-dependencies">Getting started with Fashion</a>
    and
    <a href="Getting-started-with-Fashion-Try-On.md" anchor="add-dependencies">Getting started with Fashion</a>
    guides
    </step>
    <step>Add try on compose dependency to have access to <code>FashionTryOnFlow</code>
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
    FashionTryOnFlow(
        modifier = ...,
        fashionTryOnListeners = { ... },
        fashionTryOn = { ... },
        skuForGeneration = { ... },
        skuMetaInfo = { ... },
    )
```

Where: 
- `fashionTryOnListeners` - is a instance of `FashionTryOnListeners` for observing user interaction inside flow (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/FashionTryOnListeners.kt) 
and 
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-fashion-try-on-listeners/index.html)
)
- fashionTryOn - is a instance of `FashionTryOn` for using all logic of [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-core/src/main/kotlin/com/aiuta/fashionsdk/tryon/core/FashionTryOn.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-core/com.aiuta.fashionsdk.tryon.core/-fashion-try-on/index.html)
)
- `skuForGeneration` - is SKU item for fitting on picked photo (
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-core/src/main/kotlin/com/aiuta/fashionsdk/tryon/core/domain/models/SKUGenerationItem.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-core/com.aiuta.fashionsdk.tryon.core.domain.models/-s-k-u-generation-item/index.html)
)
- `skuMetaInfo` - is meta info about providing SKU item for showing user in `FashionTryOnFlow`(
[code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-compose/src/main/kotlin/com/aiuta/fashionsdk/tryon/compose/domain/models/SKUMetaInfo.kt)
and
[api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-compose/com.aiuta.fashionsdk.tryon.compose.domain.models/-s-k-u-meta-info/index.html)
)
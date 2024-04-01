# Make digital try on

In this tutorial, you will learn how to make your first generation with [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on)


## Prerequisites

Before starting this tutorial:
- [Initialize Aiuta Try On](Getting-started-with-Fashion-Try-On.md)


## SKU Catalog items

To implement generation with [Aiuta Digital Try](https://developer.aiuta.com/products/digital-try-on) you
should have `skuId` of the item, иy which we can determine which fitting needs to be done for the photo.

Perhaps you already have a set of SKY with all the necessary meta information, then
move on to the [next section](Make-digital-try-on.md#start-generation). Otherwise, you have the option to get a list of SKUs
from the `getSKUItems()` method by passing the `categoryName`. Default category name is **aiuta_demo**

> `getSKUItems()` is a paging method, therefore it has addition `paginationOffset` and
> `paginationLimit` input parameters, with a help of which backend can determinate new page.
>
> You can use your own implementation of pagination or pay attention on [default realisation](Paging.md)
> with a help of [Paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview).
{style="note"}


## Start generation

For starting generation your need next things:
- `skuId` - which will use to fit on uploaded photo
- `skuCatalogName` (optional) - category name of the generated SKU
- And source of image - can be uri of file or url of already uploaded image

That is why interface `SKUGenerationContainer` has 2 implementation - `SKUGenerationUriContainer` and `SKUGenerationUrlContainer`

After receiving all the necessary information, feel free to call the `startSKUGeneration()` method,
passing all the data through a special wrapper `SKUGenerationContainer` and collect returned flow:
```kotlin
val aiutaTryOn: AiutaTryOn = ...

//...

val receivingFlow = aiutaTryOn.startSKUGeneration(
    container = // SKUGenerationUriContainer or SKUGenerationUrlContainer
)
```

> Be careful with possible throwing exceptions, which you can find
> in [code documentation](https://github.com/aiuta-com/android-sdk/blob/57646e50743cda6f10f8bcc54a7267da759c0a04/fashion-tryon-core/src/main/kotlin/com/aiuta/fashionsdk/tryon/core/AiutaTryOn.kt#L49)
{style="warning"}


## Observing result

After the generation starts, you need to wait for some time until the entire result is ready.
You can track the current status and get the result through by collecting returned flow from `startSKUGeneration()`.
As the result is ready, the necessary `SKUGenerationStatus.SuccessGenerationStatus` will be emitted.

To learn more about all possible `SKUGenerationStatus`, see [code](https://github.com/aiuta-com/android-sdk/blob/main/fashion-tryon-core/src/main/kotlin/com/aiuta/fashionsdk/tryon/core/domain/models/SKUGenerationStatus.kt)
or [api reference](https://aiuta-com.github.io/android-sdk-docs-api/fashion-tryon-core/com.aiuta.fashionsdk.tryon.core.domain.models/-s-k-u-generation-status/index.html)

# Paging

In this tutorial, you will learn how to use default implementation of paginng with
[Paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) and
[Fashion Digital Try On](https://developer.aiuta.com/products/digital-try-on)

## Prerequisites

Before starting this tutorial:
- [Initialize Fashion Try On](Getting-started-with-Fashion-Try-On.md)


## Add dependencies

Let's add dependencies required for default paging implementation
<procedure collapsible="false">
    <step>Firstly, add base dependencies, as described in 
    <a href="Getting-started-with-Fashion.md" anchor="add-dependencies">Getting started with Fashion</a>
    and
    <a href="Getting-started-with-Fashion-Try-On.md" anchor="add-dependencies">Getting started with Fashion</a>
    guides
    </step>
    <step>Add try on core dependency to have access to <code>FashionTryOn</code>
        <code-block lang="kotlin">
            dependencies {
                val fashionVersion: String = "%latest_fashion_version%"
                implementation("com.aiuta:fashionsdk:$fashionVersion")
                implementation("com.aiuta:fashionsdk-tryon-core:$fashionVersion")
                implementation("com.aiuta:fashionsdk-network-paging:$fashionVersion")
            }
        </code-block>
    </step>
</procedure>


## Paging usage

Now you can use `Pager` from [Paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
to implement pagination behaviour using our `ContainerPagingSource` like that:
```kotlin
val pagingFlow = Pager(
    config = PagingConfig(
        pageSize = ContainerPagingSource.DEFAULT_PAGE_SIZE,
        enablePlaceholders = false,
    ),
    pagingSourceFactory = {
        ContainerPagingSource {
            // Method, which provide PageContainer<T> items
        }
    },
).flow
```

> Example of usage such approach you can find in `implementation("com.aiuta:fashionsdk-tryon-paging:$fashionVersion")`
> to get list of SKU item. You can find it [here](https://github.com/aiuta-com/android-sdk/blob/4df9d1fa9b8800b81938196f39661c202c399aa3/fashion-tryon-paging/src/main/kotlin/com/aiuta/fashionsdk/tryon/paging/FashionTryOnPagingExtensions.kt#L14).
{style="note"}
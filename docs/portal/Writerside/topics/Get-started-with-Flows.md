# Get started with Flows

In this tutorial, you will learn how to initialize the Aiuta SDK to integrate
[Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on) directly into the UI of your application

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
                implementation("com.aiuta:fashionsdk-tryon-compose:$fashionVersion")
            }
        </code-block>
    </step>
</procedure>


That's it! Now you are ready to utilize all the features of Aiuta Digital Try On in your UI.
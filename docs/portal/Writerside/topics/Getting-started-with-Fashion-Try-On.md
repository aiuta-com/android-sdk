# Getting started with Aiuta Try On

In this tutorial, you will learn how to initialize `AiutaTryOn` powered by [Aiuta Digital Try On](https://developer.aiuta.com/products/digital-try-on)

> Code example is [here](https://github.com/aiuta-com/android-sdk/tree/main/samples/tryon)


## Prerequisites

Before starting this tutorial:
- [Initialize Aiuta SDK](Getting-started-with-Fashion.md)


## Add dependencies

Let's add dependencies required for a Aiuta Try On
<procedure collapsible="false">
    <step>Firstly, add base dependencies, as described in
    <a href="Getting-started-with-Fashion.md" anchor="add-dependencies">Getting started with Aiuta</a>
    guide
    </step>
    <step>Add try on core dependency to have access to <code>AiutaTryOn</code>
        <code-block lang="kotlin">
            dependencies {
                val fashionVersion: String = "%latest_fashion_version%"
                implementation("com.aiuta:fashionsdk:$fashionVersion")
                implementation("com.aiuta:fashionsdk-tryon-core:$fashionVersion")
            }
        </code-block>
    </step>
</procedure>


## Get Aiuta Try On
<procedure collapsible="false">
    <step>Be sure, that your initialize <b>Aiuta</b> and have instance of
    this class. Otherwise, move to <a href="Getting-started-with-Fashion.md" anchor="initialize-aiuta">initialization guide</a>
    and after that move back</step>
    <step>After that, you can use standard extension for creating new instance of <code>AiutaTryOn</code>
        <code-block lang="kotlin">
            import com.aiuta.fashionsdk.Aiuta
            import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
            import com.aiuta.fashionsdk.tryon.core.tryon
            //...
            val aiuta: Aiuta = ...
            //...
            val aiutaTryOn: AiutaTryOn = aiuta.tryon
        </code-block>
    </step>
    <step>You're ready to <a href="Make-digital-try-on.md">make your first digital try on</a>!</step>
</procedure>

# Getting started with Aiuta Analytic

In this tutorial, you will learn how to initialize `AiutaAnalytic` and observe `AnalyticEvent` from SDK


## Prerequisites

Before starting this tutorial:
- [Initialize Aiuta SDK](Getting-started-with-Fashion.md)


## Add dependencies

Let's add dependencies required for a Aiuta Analytic
<procedure collapsible="false">
    <step>Firstly, add base dependencies, as described in
    <a href="Getting-started-with-Fashion.md" anchor="add-dependencies">Getting started with Aiuta</a>
    guide
    </step>
    <step>Add analytic dependency to have access to <code>AiutaAnalytic</code>
        <code-block lang="kotlin">
            dependencies {
                val fashionVersion: String = "%latest_fashion_version%"
                implementation("com.aiuta:fashionsdk-analytic:$fashionVersion")
            }
        </code-block>
    </step>
</procedure>


## Get Aiuta Analytic
<procedure collapsible="false">
    <step>Be sure, that your initialize <b>Aiuta</b> and have instance of
    this class. Otherwise, move to <a href="Getting-started-with-Fashion.md" anchor="initialize-aiuta">initialization guide</a>
    and after that move back</step>
    <step>After that, you can use standard extension for creating new instance of <code>AiutaAnalytic</code>
        <code-block lang="kotlin">
            import com.aiuta.fashionsdk.Aiuta
            import com.aiuta.fashionsdk.analytic.AiutaAnalytic
            import com.aiuta.fashionsdk.analytic.analytic
            //...
            val aiuta: Aiuta = ...
            //...
            val aiutaAnalytic: AiutaAnalytic = aiuta.analytic
        </code-block>
    </step>
    <step>You're ready to observe <code>AnalyticEvent</code> from SDK!
        <code-block lang="kotlin">
            aiutaAnalytic.analyticFlow.collect { newEvent ->
                // Do something with new event from SDK
            }
        </code-block>
    </step>
</procedure>

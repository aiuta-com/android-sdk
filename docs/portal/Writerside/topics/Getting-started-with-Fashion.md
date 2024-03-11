# Getting started with Aiuta

In this tutorial, you will learn how to initialize the SDK and figure out how to create
your own application using all the features of [Aiuta](https://aiuta.com/)

> Code example is [here](https://github.com/aiuta-com/android-sdk/tree/main/samples/tryon)

The Aiuta SDK for Android provides the ability to use public methods provided by [Aiuta](https://aiuta.com/)
from [dev portal](https://developer.aiuta.com/).


## Prerequisites

Before starting this tutorial:
- [Install Android Studio](https://developer.android.com/studio)


## Create a new project

First of all, we need to create a project in which we will use the Aiuta SDK

<procedure collapsible="false">
    <step>On the Welcome screen, click <b>New Project</b>.
    Otherwise, from the main menu, select <b>File | New | New Project.</b></step>
    <step>Choose <b>Empty Activity</b> project</step>
    <step>Wait for synchronization of IDEA</step>
</procedure>


## Add dependencies

Let's add dependencies required for a Aiuta SDK.

<procedure collapsible="false">
    <step>Be sure, that you use <b>mavenCentral</b> for solving dependencies in
    your <b>root build.gradle.kts</b> file
        <tabs>
            <tab title="Gradle (Kotlin)">
                <code-block lang="kotlin">
                    repositories {
                        mavenCentral()
                    }
                </code-block>
            </tab>
        </tabs>
    </step>
    <step>
    Solve what the last version of Aiuta sdk
    on <a href="https://github.com/aiuta-com/android-sdk/releases">Github releases page</a>
        <note>
            <p>
                You also can check last version of artifacts on <a href="https://central.sonatype.com/search?q=com.aiuta">Central Sonatype</a>
            </p>
        </note>
    </step>
    <step>Open the <b>app/build.gradle.kts</b> file and add the following artifacts to the dependencies block:
        <tabs>
            <tab title="Gradle (Kotlin)">
                <code-block lang="kotlin">
                    dependencies {
                        val fashionVersion: String = "%latest_fashion_version%"
                        implementation("com.aiuta:fashionsdk:$fashionVersion")
                    }
                </code-block>
            </tab>
        </tabs>
        <tip>
            <p>
                Pay your attention on <a href="Using-the-Bill-of-Materials.md">using the Bill of Materials</a>
            </p>
        </tip>
    </step>
    <step>Synchronize Project with gradle file</step>
</procedure>


## Initialize Aiuta

<procedure collapsible="false">
    <step>Create <b>MainApplication</b> to initialize Aiuta entry point as follow:
        <code-block lang="kotlin">
            class MainApplication : Application() {
                lateinit var aiuta: Aiuta
                ...
                private fun initFashion() {
                    aiuta =
                        Aiuta.Builder()
                            .setApiKey(API_KEY)
                            .setApplication(this)
                            .build()
                }
                ...
            }
        </code-block>
        <note>
            <p>
                You can find your <b>API key</b> in <a href="https://developer.aiuta.com/">Aiuta dev portal</a>
            </p>
        </note>
        <warning>
            <p>
                Be sure, that you set <b>API key</b> and <b>Application</b>, otherwise it will throw exception
            </p>
        </warning>
    </step>
    <step>That's it! You are ready to explore all Aiuta possibilities</step>
</procedure>

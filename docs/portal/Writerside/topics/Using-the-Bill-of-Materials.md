# Using the Bill of Materials

In addition to the usual dependency plug-in, you can use [Bill of Materials](https://developer.android.com/jetpack/compose/bom) for better
versioning of all Fashion SDK dependencies

<tabs>
    <tab title="Gradle (Kotlin)">
    <code-block lang="kotlin">
        dependencies {
            val fashionVersion: String = "0.1.0"
            implementation(platform("com.aiuta:fashionsdk-bom:$fashionVersion"))
            implementation("com.aiuta:fashionsdk")
            // ...
        }
    </code-block>
    </tab>
</tabs>

<note>
    <p>
        You also can check last version of BOM on <a href="https://central.sonatype.com/artifact/com.aiuta/fashionsdk-bom">Central Sonatype</a>
    </p>
</note>
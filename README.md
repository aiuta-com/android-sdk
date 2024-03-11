<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="https://github.com/aiuta-com/android-sdk/assets/54765046/b104018c-9366-4cc3-8f40-dc202b6f5c8e">
    <img alt="Aiuta logo" src="https://github.com/aiuta-com/android-sdk/assets/54765046/b104018c-9366-4cc3-8f40-dc202b6f5c8e">
  </picture>
</div>

# Aiuta SDK for Android

[![Maven Central](https://img.shields.io/maven-central/v/com.aiuta/fashionsdk)](https://central.sonatype.com/search?q=com.aiuta)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.22-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

This repo distributes the [Aiuta Android SDK](https://developer.aiuta.com/products/digital-try-on/Documentation)

## Download
Aiuta SDK for Android is available on `mavenCentral()`.
```kotlin
repositories {
    mavenCentral()
}

implementation("com.aiuta:fashionsdk:<last version>")
```

## Quick Start
General
- Visit [Aiuta developer portal](https://developer.aiuta.com) for getting api key
- Initialize Aiuta
```kotlin
    val aiuta: Aiuta = Aiuta.Builder()
        .setApiKey(...)
        .setApplication(...)
        .build()
```

Digital Try On
- Initialize Aiuta Try on and start generation
```kotlin
    val aiutaTryOn: AiutaTryOn = aiuta.tryon

    aiutaTryOn.startSKUGeneration(
        //...
    )
```

## Documentation

Full documentation you can find [here](https://aiuta-com.github.io/android-sdk-docs). Also pay attention
on [API refrence](https://aiuta-com.github.io/android-sdk-docs-api)


## License

    Copyright 2024 Aiuta Contributors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

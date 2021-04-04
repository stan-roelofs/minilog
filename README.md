# minilog
<!--
[![Build Status](https://travis-ci.com/stan-roelofs/minilog.svg?branch=master)](https://travis-ci.org/stan-roelofs/minilog)
 ![Maven Central](https://img.shields.io/maven-central/v/nl.stanroelofs/minilog)
-->

A small logging library I wrote for my personal projects. Can be used with Java, Kotlin, and any other JVM language.

## Example
```kotlin
import nl.stanroelofs.minilog.Logging

class Example {

    private val logger = Logging.getLogger(Example::class.java.name)

    fun main(args: Array<String?>) {
        logger.d { "Example" }
    }
}
```

## Gradle
```
implementation 'nl.stanroelofs:minilog-1.0.1'
```

## Contributing
Issues and pull requests are welcome. 
# minilog
<!--
[![Build Status](https://travis-ci.com/stan-roelofs/minilog.svg?branch=master)](https://travis-ci.org/stan-roelofs/minilog)
 ![Maven Central](https://img.shields.io/maven-central/v/nl.stanroelofs/minilog)
-->

A simple logging library.

## Gradle
```
implementation 'nl.stanroelofs:minilog-1.0.2'
```

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


## Configuration
The `Logging` object can be modified in order to configure logging.
Note that this will only affect new `Logger` objects, so make sure you set up your configuration
**before** creating loggers using `Logging.get`.
### Configuring outputs
`Writer` objects write log messages to some output. Outputs can be configured by modifying `Logging.writers`.
#### Example
```kotlin
Logging.writers.add(SystemOutWriter())
Logging.writers.add(FileWriter(File.createTempFile("example", "txt"), false))
```

### Formatting
The `Logging.formatter` object decides how log messages are formatted. Create your own implementation of 
the `Formatter` interface to override the default behaviour.<br>
#### Example
```kotlin
Logging.formatter = object : Formatter
{
    override fun format(message: LogMessage): String {
        return "[${message.level.name}] ${message.message}"
    }
}
```

## Contributing
Issues and pull requests are welcome. 

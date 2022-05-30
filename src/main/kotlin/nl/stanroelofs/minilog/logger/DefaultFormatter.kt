package nl.stanroelofs.minilog.logger

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME

/**
 * Default formatter, example : "19:32:48.301 DEBUG \[Main\] - example message"
 * @author Stan Roelofs
 */
class DefaultFormatter : Formatter {

    val dateTime: LocalDateTime
        get() {
            return LocalDateTime.now()
        }

    override fun format(message: LogMessage): String {
        return "${dateTime.format(ISO_LOCAL_TIME)} ${message.level} [${message.tag}] - ${message.message}"
    }
}
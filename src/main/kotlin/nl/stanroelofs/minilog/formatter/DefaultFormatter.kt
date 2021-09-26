package nl.stanroelofs.minilog.formatter

import java.time.LocalDateTime

class DefaultFormatter : Formatter {

    val dateTime: LocalDateTime
        get() {
            return LocalDateTime.now()
        }

    val thread: String
        get() {
            return Thread.currentThread().name
        }

    override fun format(message: LogMessage): String {
        return "$dateTime [$thread] - ${message.tag} (${message.level}): ${message.message}"
    }
}
package nl.stanroelofs.minilog.formatter

import nl.stanroelofs.minilog.logger.Level
import java.time.LocalDateTime

class LogMessage(val tag: String, val level: Level, val message: String) {
    val dateTime: LocalDateTime
        get() {
            return LocalDateTime.now()
        }

    val thread: String
        get() {
            return Thread.currentThread().name
        }
}
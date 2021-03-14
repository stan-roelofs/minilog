package nl.stanroelofs.minilog.formatter

import nl.stanroelofs.minilog.logger.Level
import java.time.LocalTime

class DefaultFormatter : Formatter {
    override fun format(name: String, level: Level, message: String): String {
        return "${LocalTime.now()} [$name] ($level): $message"
    }
}
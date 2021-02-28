package formatter

import logger.Level
import java.time.LocalTime

class DefaultFormatter : Formatter {
    override fun format(tag: String, level: Level, message: String): String {
        return "${LocalTime.now()} [$tag] ($level): $message"
    }
}
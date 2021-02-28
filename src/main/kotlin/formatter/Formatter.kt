package formatter

import logger.Level

interface Formatter {
    fun format(tag: String, level: Level, message: String) : String
}
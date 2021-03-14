package nl.stanroelofs.minilog.formatter

import nl.stanroelofs.minilog.logger.Level

interface Formatter {
    fun format(name: String, level: Level, message: String) : String
}
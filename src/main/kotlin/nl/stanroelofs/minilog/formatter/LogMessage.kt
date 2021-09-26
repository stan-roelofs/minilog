package nl.stanroelofs.minilog.formatter

import nl.stanroelofs.minilog.logger.Level

data class LogMessage(val tag: String, val level: Level, val message: String)
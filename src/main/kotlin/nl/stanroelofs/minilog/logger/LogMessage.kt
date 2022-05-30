package nl.stanroelofs.minilog.logger

/**
 * Stores information about a log message
 * @author Stan Roelofs
 */
data class LogMessage(val tag: String, val level: Level, val message: String)
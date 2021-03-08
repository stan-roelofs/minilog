package nl.stanroelofs.minilog.handler

import nl.stanroelofs.minilog.logger.Level

/**
 * Interface for an object that handles logging output.
 */
interface Writer {
    fun writeLog(tag: String, level: Level, formattedMessage: String)
}
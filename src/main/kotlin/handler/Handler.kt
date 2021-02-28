package handler

import logger.Level

/**
 * Interface for an object that handles logging output.
 */
interface Handler {
    fun writeLog(tag: String, level: Level, formattedMessage: String)
}
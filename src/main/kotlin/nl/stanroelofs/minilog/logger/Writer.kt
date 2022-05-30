package nl.stanroelofs.minilog.logger

/**
 * Handles logging output.
 * @author Stan Roelofs
 */
interface Writer {
    fun writeLog(formattedMessage: String)
}
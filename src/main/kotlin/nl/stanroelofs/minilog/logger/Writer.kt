package nl.stanroelofs.minilog.logger

/**
 * Handles logging output.
 * @author Stan Roelofs
 */
interface Writer {
    /**
     * Writes a log message to some output
     * @param formattedMessage The message that will be logged
     */
    fun writeLog(formattedMessage: String)
}
package nl.stanroelofs.minilog.writer

/**
 * Interface for an object that handles logging output.
 */
interface Writer {
    fun writeLog(formattedMessage: String)
}
package nl.stanroelofs.minilog.writer

/**
 * Interface for an object that handles logging output.
 */
interface Writer {
    //TODO
    fun open()
    // TODO
    fun writeLog(formattedMessage: String)
    // TODO
    fun close()
}
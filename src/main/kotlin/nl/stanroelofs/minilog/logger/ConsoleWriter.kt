package nl.stanroelofs.minilog.logger

/**
 * Writes log messages to stdout
 * @author Stan Roelofs
 */
class ConsoleWriter : Writer {
    override fun writeLog(formattedMessage: String) {
        println(formattedMessage)
    }
}
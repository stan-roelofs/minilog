package nl.stanroelofs.minilog.logger

/**
 * Transforms a log message object into a string
 * @author Stan Roelofs
 */
interface Formatter {
    fun format(message: LogMessage) : String
}
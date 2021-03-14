package nl.stanroelofs.minilog.logger

import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.writer.Writer

/**
 * A logger object which
 *
 * @param name a name for this logger
 * @param writer an object implementing the [Writer] interface which will be used to write log messages to an output
 * @param formatter an object implementing the [Formatter] interface which will be used to format log messages
 *
 * @author Stan Roelofs
 * @version 1.0
 */
class Logger(val name: String, private val writer: Writer, private val formatter: Formatter) {

    /** The current logging level for this logger
     * @see Level
     */
    var level: Level = Level.OFF

    /**
     * Returns whether logging with [level] is enabled
     *
     * @param level the logging level
     * @return whether this logger is enabled for the given level
     * @see Level
     */
    fun levelEnabled(level: Level) : Boolean = level >= this.level

    val debugEnabled
        /** Returns whether debug messages are enabled for this logger */
        get() = levelEnabled(Level.DEBUG)

    val infoEnabled
        /** Returns whether info messages are enabled for this logger */
        get() = levelEnabled(Level.INFO)

    val warningEnabled
        /** Returns whether warning messages are enabled for this logger */
        get() = levelEnabled(Level.WARNING)

    val errorEnabled
        /** Returns whether error messages are enabled for this logger */
        get() = levelEnabled(Level.ERROR)


    fun log(level: Level, message: String) {
        writer.writeLog(formatter.format(name, level, message))
    }

    /** Writes a debug message to some output
     *
     * @param message the log message
     */
    fun debug(message: String) {
        if (debugEnabled)
            log(Level.DEBUG, message)
    }

    /** Alias for [debug] */
    fun d(message: String) = debug(message)

    /** Writes an info message to some output
     *
     * @param message the log message
     */
    fun info(message: String) {
        if (infoEnabled)
            log(Level.INFO, message)
    }

    /** Alias for [info] */
    fun i(message: String) = info(message)

    /** Writes a warning message to some output
     *
     * @param message the log message
     */
    fun warning(message: String) {
        if (warningEnabled)
            log(Level.WARNING, message)
    }

    /** Alias for [warning] */
    fun w(message: String) = warning(message)

    /** Writes an error message to some output
     *
     * @param message the log message
     * @see i
     */
    fun error(message: String) {
        if (errorEnabled)
            log(Level.ERROR, message)
    }

    /** Alias for [error] */
    fun e(message: String) = error(message)
}
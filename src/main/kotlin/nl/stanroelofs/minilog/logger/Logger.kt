package nl.stanroelofs.minilog.logger

/** A logger with a name/tag that writes logs using a [Writer] and [Formatter].
 *
 * @param name the name for this logger
 * @param level the logging level, this will determine whether logs are actually written
 * @param writer an object implementing the [Writer] interface which will be used to write log messages to an output
 * @param formatter an object implementing the [Formatter] interface which will be used to format log messages
 *
 * @author Stan Roelofs
 */
class Logger constructor(var name: String, var level: Level, var writer: Writer, var formatter: Formatter) {

    /**
     * Returns whether logging with [level] is enabled.
     *
     * @param level the logging level
     * @return true when the current level is greater than or equal to [level]
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


    private fun log(level: Level, message: String) {
        writer.writeLog(formatter.format(LogMessage(name, level, message)))
    }

    private fun log(level: Level, message: () -> String) {
        log(level, message())
    }

    /** Logs a message at the debug level.
     *
     * @param message the log message
     */
    fun debug(message: String) {
        if (debugEnabled)
            log(Level.DEBUG, message)
    }

    /** Alias for [debug] */
    fun d(message: String) = debug(message)

    /** Logs a message at the debug level. The message will only be evaluated if debug messages
     * are enabled by [level].
     *
     * @param message a lambda function that produces the message
     */
    fun debug(message: () -> String) {
        if (debugEnabled)
            log(Level.DEBUG, message)
    }

    /** alias for [debug] */
    fun d(message: () -> String) = debug(message)

    /** Logs a message at the info level.
     *
     * @param message the log message
     */
    fun info(message: String) {
        if (infoEnabled)
            log(Level.INFO, message)
    }

    /** Alias for [info] */
    fun i(message: String) = info(message)

    /** Logs a message at the info level. The message will only be evaluated if info messages
     * are enabled by [level].
     *
     * @param message a lambda function that produces the message
     */
    fun info(message: () -> String) {
        if (infoEnabled)
            log(Level.INFO, message)
    }

    /** alias for [info] */
    fun i(message: () -> String) = info(message)

    /** Logs a message at the warning level.
     *
     * @param message the log message
     */
    fun warning(message: String) {
        if (warningEnabled)
            log(Level.WARNING, message)
    }

    /** Alias for [warning] */
    fun w(message: String) = warning(message)

    /** Logs a message at the warning level. The message will only be evaluated if warning messages
     * are enabled by [level].
     *
     * @param message a lambda function that produces the message
     */
    fun warning(message: () -> String) {
        if (warningEnabled)
            log(Level.WARNING, (message))
    }

    /** alias for [warning] */
    fun w(message: () -> String) = warning(message)

    /** Logs a message at the error level.
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

    /** Logs a message at the error level. The message will only be evaluated if error messages
     * are enabled by [level].
     *
     * @param message a lambda function that produces the message
     */
    fun error(message: () -> String) {
        if (errorEnabled)
            log(Level.ERROR, message)
    }

    /** alias for [error] */
    fun e(message: () -> String) = error(message)
}
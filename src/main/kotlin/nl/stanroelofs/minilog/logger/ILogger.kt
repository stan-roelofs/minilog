package nl.stanroelofs.minilog.logger

/**
 * Interface for a logger class
 *
 * @author Stan Roelofs
 * @version 1.0
 */
interface ILogger {

    /** The current logging level for this logger
     * @see Level
     */
    var currentLevel: Level
    /**
     * Returns whether logging with [level] is enabled
     *
     * @param level the logging level
     * @return whether this logger is enabled for the given level
     * @see Level
     */
    fun levelEnabled(level: Level) : Boolean = currentLevel.ordinal >= level.ordinal

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

    /** Writes a debug message to some output
     *
     * @param message the log message
     */
    fun debug(message: String)

    /** Alias for [debug] */
    fun d(message: String)

    /** Writes an info message to some output
     *
     * @param message the log message
     */
    fun info(message: String)

    /** Alias for [info] */
    fun i(message: String)

    /** Writes a warning message to some output
     *
     * @param message the log message
     */
    fun warning(message: String)

    /** Alias for [warning] */
    fun w(message: String)

    /** Writes an error message to some output
     *
     * @param message the log message
     * @see i
     */
    fun error(message: String)

    /** Alias for [error] */
    fun e(message: String)
}
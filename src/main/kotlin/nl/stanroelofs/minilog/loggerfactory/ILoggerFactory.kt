package nl.stanroelofs.minilog.loggerfactory

import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.Logger
import nl.stanroelofs.minilog.writer.Writer

/**
 * Interface for a logger factory which returns a [Logger] object given a name
 *
 * @author Stan Roelofs
 * @version 1.0
 */
interface ILoggerFactory {
    /** A [Writer] which will be used as the writer for new loggers created using this factory */
    var defaultWriter: Writer

    /** A [Formatter] which will be used as the formatter for new loggers created using this factory */
    var defaultFormatter: Formatter

    /** The default [Level] of all loggers created by this factory */
    var defaultLevel: Level

    /** A collection containing all loggers created by this factory */
    val loggers: Collection<Logger>

    /** Returns a logger with the specified name
     *
     *  @param name the name of the logger
     */
    fun getLogger(name: String) : Logger
}
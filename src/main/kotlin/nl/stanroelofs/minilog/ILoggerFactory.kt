package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.logger.Logger

/**
 * Interface for a logger factory which returns a [Logger] object given a name
 *
 * @author Stan Roelofs
 */
interface ILoggerFactory {
    /** A collection containing all loggers created by this factory */
    val loggers: Collection<Logger>

    /** Returns a logger with the specified name
     *
     *  @param name the name of the logger
     */
    fun get(name: String) : Logger
}
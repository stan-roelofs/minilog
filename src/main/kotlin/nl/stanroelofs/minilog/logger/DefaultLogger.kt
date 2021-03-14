package nl.stanroelofs.minilog.logger

import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.writer.Writer

/**
 *
 * @param name the name for this logger
 * @param writer an object implementing the [Writer] interface which will be used to write log messages to an output
 * @param formatter an object implementing the [Formatter] interface which will be used to format log messages
 *
 * @author Stan Roelofs
 * @version 1.0
 */
internal class DefaultLogger(override val name: String, override val writer: Writer, override val formatter: Formatter) : Logger {
    override var level: Level = Level.OFF
}
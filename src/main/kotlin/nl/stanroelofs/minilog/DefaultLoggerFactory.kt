package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.formatter.DefaultFormatter
import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.logger.Logger
import nl.stanroelofs.minilog.writer.ConsoleWriter
import nl.stanroelofs.minilog.writer.Writer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

internal open class DefaultLoggerFactory : LoggerFactory {
    override var defaultWriter : Writer = ConsoleWriter()
    override var defaultFormatter : Formatter = DefaultFormatter()

    private val loggerMap : ConcurrentMap<String, Logger> = ConcurrentHashMap()

    /** A map containing all loggers which were created by this factory. */
    val loggers : Map<String, Logger>
        get() = HashMap(loggerMap)

    override fun getLogger(name: String) : Logger {
        val logger = loggerMap[name] ?: Logger(name, defaultWriter, defaultFormatter)
        loggerMap.putIfAbsent(name, logger)
        return logger
    }
}
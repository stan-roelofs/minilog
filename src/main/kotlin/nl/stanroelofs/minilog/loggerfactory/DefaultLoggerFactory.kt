package nl.stanroelofs.minilog.loggerfactory

import nl.stanroelofs.minilog.formatter.DefaultFormatter
import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.logger.DefaultLogger
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.Logger
import nl.stanroelofs.minilog.writer.ConsoleWriter
import nl.stanroelofs.minilog.writer.Writer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

internal open class DefaultLoggerFactory : LoggerFactory {
    override var defaultWriter : Writer = ConsoleWriter()
    override var defaultFormatter : Formatter = DefaultFormatter()
    override var defaultLevel = Level.DEBUG

    private val loggerMap : ConcurrentMap<String, Logger> = ConcurrentHashMap()

    /** A map containing all loggers which were created by this factory. */
    val loggers : Map<String, Logger>
        get() = HashMap(loggerMap)

    override fun getLogger(name: String) : Logger {
        if (loggerMap.containsKey(name)) {
            return loggerMap[name]!!
        }
        val logger = DefaultLogger(name, defaultWriter, defaultFormatter)
        logger.level = defaultLevel
        loggerMap.putIfAbsent(name, logger)
        return logger
    }
}
package nl.stanroelofs.minilog.loggerfactory

import nl.stanroelofs.minilog.formatter.DefaultFormatter
import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.Logger
import nl.stanroelofs.minilog.writer.ConsoleWriter
import nl.stanroelofs.minilog.writer.Writer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

open class LoggerFactory : ILoggerFactory {
    override var defaultWriter : Writer = ConsoleWriter()
    override var defaultFormatter : Formatter = DefaultFormatter()
    override var defaultLevel = Level.DEBUG

    private val loggerMap : ConcurrentMap<String, Logger> = ConcurrentHashMap()

    /** A map containing all loggers which were created by this factory. */
    override val loggers: Collection<Logger>
        get() = loggerMap.values

    override fun getLogger(name: String) : Logger {
        var logger = loggerMap[name]

        if (logger == null) {
            logger = Logger(name, defaultLevel, defaultWriter, defaultFormatter)
            val existingLogger = loggerMap.putIfAbsent(name, logger)
            return existingLogger ?: logger
        }

        return logger
    }
}
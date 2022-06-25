package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.logger.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * @author Stan Roelofs
 */
open class LoggerFactory : ILoggerFactory {
    val writers : MutableCollection<Writer> = ArrayList()
    var formatter : Formatter = DefaultFormatter()
    var level = Level.DEBUG

    private var combinedWriter = object : Writer {
        override fun writeLog(formattedMessage: String) {
            for (writer in writers)
            {
                writer.writeLog(formattedMessage)
            }
        }
    }

    private val loggerMap : ConcurrentMap<String, Logger> = ConcurrentHashMap()

    /** A map containing all loggers which were created by this factory. */
    override val loggers: Collection<Logger>
        get() = loggerMap.values

    override fun get(name: String) : Logger {
        var logger = loggerMap[name]

        if (logger == null) {
            logger = Logger(name, level, combinedWriter, formatter)
            val existingLogger = loggerMap.putIfAbsent(name, logger)
            return existingLogger ?: logger
        }

        return logger
    }
}
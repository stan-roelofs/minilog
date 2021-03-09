package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.formatter.DefaultFormatter
import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.handler.ConsoleWriter
import nl.stanroelofs.minilog.handler.Writer
import nl.stanroelofs.minilog.logger.ILogger
import nl.stanroelofs.minilog.logger.Logger

object Logging : LoggerFactory {
    override var defaultWriter : Writer = ConsoleWriter()
    override var defaultFormatter : Formatter = DefaultFormatter()

    override fun getLogger(name: String) : ILogger {
        return Logger(name, defaultFormatter, defaultWriter)
    }
}
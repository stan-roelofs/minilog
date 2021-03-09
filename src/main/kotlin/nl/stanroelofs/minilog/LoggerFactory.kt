package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.handler.Writer
import nl.stanroelofs.minilog.logger.ILogger

interface LoggerFactory {
    var defaultWriter: Writer
    var defaultFormatter: Formatter

    fun getLogger(name: String) : ILogger
}
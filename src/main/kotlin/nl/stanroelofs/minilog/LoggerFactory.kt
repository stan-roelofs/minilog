package nl.stanroelofs.minilog

import nl.stanroelofs.minilog.logger.ILogger

interface LoggerFactory {
    fun getLogger(name: String) : ILogger
}
import logger.ILogger

interface LoggerFactory {
    fun getLogger(name: String) : ILogger
}
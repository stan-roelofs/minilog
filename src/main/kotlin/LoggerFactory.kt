import logger.Logger

interface LoggerFactory {
    fun getLogger(name: String) : Logger
}
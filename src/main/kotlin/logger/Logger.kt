package logger

import formatter.Formatter
import handler.Handler

class Logger(private val tag: String, private val formatter : Formatter, private val handler : Handler) : ILogger {

    private fun log(level: Level, message: String) {
        handler.writeLog(tag, level, formatter.format(tag, level, message))
    }

    override fun debug(message: String) = log(Level.DEBUG, message)
    override fun d(message: String) = debug(message)

    override fun info(message: String) = log(Level.INFO, message)
    override fun i(message: String) = info(message)

    override fun warning(message: String) = log(Level.WARNING, message)
    override fun w(message: String) = warning(message)

    override fun error(message: String) = log(Level.ERROR, message)
    override fun e(message: String) = error(message)
}



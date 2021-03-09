package nl.stanroelofs.minilog.logger

import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.handler.Writer

class Logger(private val tag: String, private val formatter : Formatter, private val handler : Writer) : ILogger {

    override var currentLevel = Level.OFF

    private fun log(level: Level, message: String) {
        handler.writeLog(tag, level, formatter.format(tag, level, message))
    }

    override fun debug(message: String){
        if (debugEnabled)
            log(Level.DEBUG, message)
    }
    override fun d(message: String) = debug(message)

    override fun info(message: String) {
        if (infoEnabled)
            log(Level.INFO, message)
    }
    override fun i(message: String) = info(message)

    override fun warning(message: String) {
        if (warningEnabled)
            log(Level.WARNING, message)
    }
    override fun w(message: String) = warning(message)

    override fun error(message: String) {
        if (errorEnabled)
            log(Level.ERROR, message)
    }
    override fun e(message: String) = error(message)
}



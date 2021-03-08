package nl.stanroelofs.minilog.handler

import nl.stanroelofs.minilog.logger.Level

class ConsoleWriter : Writer {
    override fun writeLog(tag: String, level: Level, formattedMessage: String) {
        println(formattedMessage)
    }

}
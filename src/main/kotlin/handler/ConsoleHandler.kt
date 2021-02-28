package handler

import logger.Level

class ConsoleHandler : Handler {
    override fun writeLog(tag: String, level: Level, formattedMessage: String) {
        println(formattedMessage)
    }

}
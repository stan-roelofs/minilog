package nl.stanroelofs.minilog.writer

class ConsoleWriter : Writer {
    override fun writeLog(formattedMessage: String) {
        println(formattedMessage)
    }

}
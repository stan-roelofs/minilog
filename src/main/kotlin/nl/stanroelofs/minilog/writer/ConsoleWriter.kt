package nl.stanroelofs.minilog.writer

class ConsoleWriter : Writer {
    override fun open() {
    }

    override fun writeLog(formattedMessage: String) {
        println(formattedMessage)
    }

    override fun close() {
    }
}
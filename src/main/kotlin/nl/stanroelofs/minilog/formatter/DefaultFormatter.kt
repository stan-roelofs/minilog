package nl.stanroelofs.minilog.formatter

class DefaultFormatter : Formatter {
    override fun format(message: LogMessage): String {
        return "${message.dateTime} [${message.thread}] - ${message.tag} (${message.level}): ${message.message}"
    }
}
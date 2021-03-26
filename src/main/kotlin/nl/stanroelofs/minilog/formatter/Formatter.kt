package nl.stanroelofs.minilog.formatter

interface Formatter {
    fun format(message: LogMessage) : String
}
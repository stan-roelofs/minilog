package logger

interface ILogger {
    fun debug(message: String)
    fun info(message: String)
    fun warning(message: String)
    fun error(message: String)
}
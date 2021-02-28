package logger

interface ILogger {
    fun debug(message: String)
    fun d(message: String)

    fun info(message: String)
    fun i(message: String)

    fun warning(message: String)
    fun w(message: String)

    fun error(message: String)
    fun e(message: String)
}
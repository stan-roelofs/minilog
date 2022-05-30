package nl.stanroelofs.minilog.logger

import java.io.File
import java.io.IOException

class FileWriter(file: File) : Writer {
    val fileWriter = file.bufferedWriter()

    init {
        fileWriter.write("")
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                fileWriter.close()
            }
        })
    }

    override fun writeLog(formattedMessage: String) {
        try {
            fileWriter.append(formattedMessage)
        } catch (_: IOException) {
        }
    }

    /** In order to limit file I/O log messages are buffered, calling this function will flush immediately
     * @throws IOException
     */
    fun flush() {
        fileWriter.flush()
    }
}
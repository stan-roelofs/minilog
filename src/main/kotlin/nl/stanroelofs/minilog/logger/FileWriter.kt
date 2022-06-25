package nl.stanroelofs.minilog.logger

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * Writes log messages to a file.
 *
 * @param file the file that log messages should be written to
 * @param append if true, messages will be written to the end of the file rather than the beginning.
 */
class FileWriter(file: File, append: Boolean = false) : Writer {

    private val fileWriter = BufferedWriter(FileWriter(file, append))

    init {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                fileWriter.close()
            }
        })
    }

    override fun writeLog(formattedMessage: String) {
        try {
            fileWriter.append(formattedMessage)
            fileWriter.newLine()
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
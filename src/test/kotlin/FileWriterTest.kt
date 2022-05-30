import nl.stanroelofs.minilog.Logging
import nl.stanroelofs.minilog.logger.FileWriter
import nl.stanroelofs.minilog.logger.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.LogMessage
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class FileWriterTest {

    @Test
    fun testOutput() {
        val logger = Logging.get("test")
        val message = "message"
        val file = File.createTempFile("test", "test")
        val writer = FileWriter(file)
        logger.writer = writer
        logger.formatter = object : Formatter {
            override fun format(message: LogMessage): String {
                return message.message
            }
        }
        logger.level = Level.DEBUG
        logger.d(message)
        writer.flush()

        val result = file.readText()
        assertEquals(message, result)
    }
}
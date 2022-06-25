import nl.stanroelofs.minilog.Logging
import nl.stanroelofs.minilog.logger.FileWriter
import nl.stanroelofs.minilog.logger.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.LogMessage
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileWriterTest {
    @Test
    fun testOutput() {
        val logger = Logging.get("test")
        val message = "message"
        val file = File.createTempFile("test", "test")
        val writer = FileWriter(file, true)
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
        assertEquals(message + System.lineSeparator(), result)
    }

    @Test
    fun testNotClearFile() {
        val file = File.createTempFile("test", "test")
        file.writer().apply {
            write("test")
            flush()
            close()
        }
        assertTrue(file.readText().isNotEmpty())
        FileWriter(file, true).flush()
        assertTrue(file.readText().isNotEmpty())
    }

    @Test
    fun testClearFile() {
        val file = File.createTempFile("test", "test")
        file.writer().apply {
            write("test")
            flush()
            close()
        }
        assertTrue(file.readText().isNotEmpty())
        FileWriter(file, false).flush()
        assertTrue(file.readText().isEmpty())
    }
}
import nl.stanroelofs.minilog.LoggerFactory
import nl.stanroelofs.minilog.logger.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.LogMessage
import nl.stanroelofs.minilog.logger.Writer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoggerFactoryTest {

    private var factory = LoggerFactory()

    @Before
    fun resetFactory() {
        factory = LoggerFactory()
        factory.level = Level.DEBUG
    }

    @Test
    fun getLoggerCorrectName() {
        val logger = factory.get("test")
        assertEquals("test", logger.name)
    }

    @Test
    fun getLoggersEmpty() {
        val loggers = factory.loggers
        assertTrue(loggers.isEmpty())
    }

    @Test
    fun addLogger() {
        val logger = factory.get("test")
        assertTrue(factory.loggers.contains(logger))
        assertEquals(logger, factory.loggers.find { it == logger})
    }

    @Test
    fun getExistingLogger() {
        val logger = factory.get("test")
        val logger2 = factory.get("test")
        assertEquals(logger, logger2)
    }

    @Test
    fun setWriter() {
        val customWriter = object : Writer {
            var gotMessage = false
            override fun writeLog(formattedMessage: String) {
                gotMessage = true
            }
        }
        factory.writers.add(customWriter)
        factory.get("test").i("")
        assertTrue(customWriter.gotMessage)
    }

    @Test
    fun setFormatter() {
        val customFormatter = object : Formatter {
            var gotMessage = false
            override fun format(message: LogMessage): String {
                gotMessage = true
                return message.message
            }
        }

        factory.formatter = customFormatter
        factory.get("test").i("")
        assertTrue(customFormatter.gotMessage)
    }

    @Test
    fun defaultLevel() {
        assertEquals(Level.DEBUG, factory.level)
    }

    @Test
    fun setLevel() {
        factory.level = Level.OFF
        val logger = factory.get("test")
        assertEquals(Level.OFF, logger.level)
    }

    @Test
    fun multipleWriters()
    {
        val formatter = object : Formatter
        {
            override fun format(message: LogMessage): String {
                return message.message
            }
        }
        class WriterMock : Writer
        {
            var lastMessage = ""
            override fun writeLog(formattedMessage: String) {
                lastMessage = formattedMessage
            }
        }
        val writer = WriterMock()
        val writer2 = WriterMock()

        factory.formatter = formatter
        factory.writers.add(writer)
        factory.writers.add(writer2)

        factory.get("test").i("test")

        assertEquals("test", writer.lastMessage)
        assertEquals("test", writer2.lastMessage)
    }
}
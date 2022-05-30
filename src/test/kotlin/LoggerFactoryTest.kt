import nl.stanroelofs.minilog.LoggerFactory
import nl.stanroelofs.minilog.logger.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.LogMessage
import nl.stanroelofs.minilog.logger.Writer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals

class LoggerFactoryTest {

    private var factory = LoggerFactory()

    @Before
    fun resetFactory() {
        factory = LoggerFactory()
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
            override fun writeLog(formattedMessage: String) {
            }
        }

        val logger = factory.get("test")
        factory.defaultWriter = customWriter
        assertNotEquals(logger.writer, customWriter)

        assertEquals(customWriter, factory.get("test2").writer)
    }

    @Test
    fun setFormatter() {
        val customFormatter = object : Formatter {
            override fun format(message: LogMessage): String {
                return message.message
            }
        }

        val logger = factory.get("test")
        factory.defaultFormatter = customFormatter
        assertNotEquals(logger.formatter, customFormatter)

        assertEquals(customFormatter, factory.get("test2").formatter)
    }

    @Test
    fun defaultLevel() {
        assertEquals(Level.DEBUG, factory.defaultLevel)
    }

    @Test
    fun setLevel() {
        factory.defaultLevel = Level.OFF
        val logger = factory.get("test")
        assertEquals(Level.OFF, logger.level)
    }
}
import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.loggerfactory.DefaultLoggerFactory
import nl.stanroelofs.minilog.writer.Writer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals

class DefaultLoggerFactoryTest {

    private var factory = DefaultLoggerFactory()

    @Before
    fun resetFactory() {
        factory = DefaultLoggerFactory()
    }

    @Test
    fun getLoggerCorrectName() {
        val logger = factory.getLogger("test")
        assertEquals("test", logger.name)
    }

    @Test
    fun getLoggersEmpty() {
        val loggers = factory.loggers
        assertTrue(loggers.isEmpty())
    }

    @Test
    fun addLogger() {
        val logger = factory.getLogger("test")
        assertTrue(factory.loggers.containsKey("test"))
        assertEquals(logger, factory.loggers["test"])
    }

    @Test
    fun getExistingLogger() {
        val logger = factory.getLogger("test")
        val logger2 = factory.getLogger("test")
        assertEquals(logger, logger2)
    }

    @Test
    fun setWriter() {
        val customWriter = object : Writer {
            override fun writeLog(formattedMessage: String) {
            }
        }

        val logger = factory.getLogger("test")
        factory.defaultWriter = customWriter
        assertNotEquals(logger.writer, customWriter)

        assertEquals(customWriter, factory.getLogger("test2").writer)
    }

    @Test
    fun setFormatter() {
        val customFormatter = object : Formatter {
            override fun format(name: String, level: Level, message: String): String {
                return ""
            }
        }

        val logger = factory.getLogger("test")
        factory.defaultFormatter = customFormatter
        assertNotEquals(logger.formatter, customFormatter)

        assertEquals(customFormatter, factory.getLogger("test2").formatter)
    }

    @Test
    fun defaultLevel() {
        assertEquals(Level.DEBUG, factory.defaultLevel)
    }

    @Test
    fun setLevel() {
        factory.defaultLevel = Level.OFF
        val logger = factory.getLogger("test")
        assertEquals(Level.OFF, logger.level)
    }
}
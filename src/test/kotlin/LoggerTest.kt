import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.formatter.LogMessage
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.Logger
import nl.stanroelofs.minilog.writer.Writer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoggerTest {

    private var messages = ArrayList<String>()

    private val writer = object : Writer {
        override fun open() {
        }

        override fun writeLog(formattedMessage: String) {
            messages.add(formattedMessage)
        }

        override fun close() {
        }
    }

    private val formatter = object : Formatter {
        override fun format(message: LogMessage): String {
            return message.message
        }
    }

    private lateinit var logger : Logger

    @Before
    fun createLogger() {
        logger = Logger("test", Level.OFF, writer, formatter)
        messages.clear()
    }

    @Test
    fun defaultLevel() {
        assertEquals(Level.OFF, logger.level)
    }

    @Test
    fun debug() {
        logger.level = Level.DEBUG
        logger.d("1")
        logger.debug("2")
        logger.d{"3"}
        logger.debug{"4"}

        assertEquals(4, messages.size)
        assertEquals("1", messages[0])
        assertEquals("2", messages[1])
        assertEquals("3", messages[2])
        assertEquals("4", messages[3])
    }

    @Test
    fun info() {
        logger.level = Level.INFO
        logger.i("1")
        logger.info("2")
        logger.i{"3"}
        logger.info{"4"}

        assertEquals(4, messages.size)
        assertEquals("1", messages[0])
        assertEquals("2", messages[1])
        assertEquals("3", messages[2])
        assertEquals("4", messages[3])
    }

    @Test
    fun warning() {
        logger.level = Level.WARNING
        logger.w("1")
        logger.warning("2")
        logger.w{"3"}
        logger.warning{"4"}

        assertEquals(4, messages.size)
        assertEquals("1", messages[0])
        assertEquals("2", messages[1])
        assertEquals("3", messages[2])
        assertEquals("4", messages[3])
    }

    @Test
    fun error() {
        logger.level = Level.ERROR
        logger.e("1")
        logger.error("2")
        logger.e{"3"}
        logger.error{"4"}

        assertEquals(4, messages.size)
        assertEquals("1", messages[0])
        assertEquals("2", messages[1])
        assertEquals("3", messages[2])
        assertEquals("4", messages[3])
    }

    @Test
    fun levelOff() {
        logger.level = Level.OFF
        logger.d("debug")
        logger.i("info")
        logger.w("warning")
        logger.e("error")
        assertTrue(messages.isEmpty())
    }

    @Test
    fun levelError() {
        logger.level = Level.ERROR
        logger.d("debug")
        logger.i("info")
        logger.w("warning")
        logger.e("error")
        assertEquals(1, messages.size)
        assertEquals("error", messages[0])
    }

    @Test
    fun levelWarning() {
        logger.level = Level.WARNING
        logger.d("debug")
        logger.i("info")
        logger.w("warning")
        logger.e("error")
        assertEquals(2, messages.size)
        assertEquals("warning", messages[0])
        assertEquals("error", messages[1])
    }

    @Test
    fun levelInfo() {
        logger.level = Level.INFO
        logger.d("debug")
        logger.i("info")
        logger.w("warning")
        logger.e("error")
        assertEquals(3, messages.size)
        assertEquals("info", messages[0])
        assertEquals("warning", messages[1])
        assertEquals("error", messages[2])
    }

    @Test
    fun levelDebug() {
        logger.level = Level.DEBUG
        logger.d("debug")
        logger.i("info")
        logger.w("warning")
        logger.e("error")
        assertEquals(4, messages.size)
        assertEquals("debug", messages[0])
        assertEquals("info", messages[1])
        assertEquals("warning", messages[2])
        assertEquals("error", messages[3])
    }
}
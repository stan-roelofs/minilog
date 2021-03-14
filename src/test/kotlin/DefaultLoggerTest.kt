import nl.stanroelofs.minilog.formatter.Formatter
import nl.stanroelofs.minilog.logger.DefaultLogger
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.logger.Logger
import nl.stanroelofs.minilog.writer.Writer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DefaultLoggerTest {

    private var messages = ArrayList<String>()

    private val writer = object : Writer {
        override fun writeLog(formattedMessage: String) {
            messages.add(formattedMessage)
        }
    }

    private val formatter = object : Formatter {
        override fun format(name: String, level: Level, message: String): String {
            return message
        }
    }

    private var logger : Logger = DefaultLogger("test", writer, formatter)

    @Before
    fun createLogger() {
        logger = DefaultLogger("test", writer, formatter)
        messages.clear()
    }

    @Test
    fun defaultLevel() {
        assertEquals(Level.DEBUG, logger.level)
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
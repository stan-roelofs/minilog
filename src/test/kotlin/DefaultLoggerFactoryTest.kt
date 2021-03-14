import nl.stanroelofs.minilog.DefaultLoggerFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

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
}
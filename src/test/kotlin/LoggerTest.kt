import org.junit.Test

class LoggerTest {
    @Test
    fun testGetLogger() {
        val logger = Logging.getLogger("test")
    }
}
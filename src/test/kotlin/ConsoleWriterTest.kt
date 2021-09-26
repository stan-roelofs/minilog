import nl.stanroelofs.minilog.Logging
import nl.stanroelofs.minilog.logger.Level
import nl.stanroelofs.minilog.writer.ConsoleWriter
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.io.PrintStream
import java.nio.charset.StandardCharsets
import kotlin.test.assertTrue

class ConsoleWriterTest {

    companion object {
        val messages = MutableList<Byte>(0) { 0 }

        val stream = object : PrintStream(System.out) {
            override fun write(buf: ByteArray, off: Int, len: Int) {
                for (i in 0 until len)
                    messages.add(buf[off + i])
            }
        }

        private var systemOut: PrintStream? = null

        @BeforeClass
        @JvmStatic
        fun setup() {
            systemOut = System.out
            System.setOut(stream)
        }

        @AfterClass
        @JvmStatic
        fun close() {
            System.setOut(systemOut)
        }

        @Before
        fun clearMessages() {
            Logging.defaultWriter = ConsoleWriter()
            Logging.defaultLevel = Level.DEBUG

            messages.clear()
            assertTrue(messages.isEmpty())
        }
    }

    @Test
    fun testOutput() {
        Logging.get("test").d("test message")
        val str = String(messages.toByteArray(), StandardCharsets.UTF_8)
        assertTrue(str.contains("test message"))
    }
}
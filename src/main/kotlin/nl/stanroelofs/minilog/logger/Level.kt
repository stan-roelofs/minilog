package nl.stanroelofs.minilog.logger

/**
 * A logging level, each log message has a level indicating the importance of the message.
 * @author Stan Roelofs
 */
enum class Level(private val stringValue: String) {
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    OFF("OFF");

    override fun toString(): String {
        return stringValue
    }
}
package nl.stanroelofs.minilog.logger

enum class Level(private val stringValue: String) {
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");

    override fun toString(): String {
        return stringValue
    }
}
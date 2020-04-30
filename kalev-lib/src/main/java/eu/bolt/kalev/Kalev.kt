package eu.bolt.kalev

object Kalev {

    const val V = "v"
    const val D = "d"
    const val I = "i"
    const val W = "w"
    const val E = "e"

    private val consumers = mutableListOf<Kalevipoeg>()

    @JvmStatic
    fun d(message: String) {
        LogEntry().log(message, D)
    }

    @JvmStatic
    fun d(throwable: Throwable, message: String) {
        with(throwable).log(message, D)
    }

    @JvmStatic
    fun e(message: String) {
        LogEntry().log(message, E)
    }

    @JvmStatic
    fun e(throwable: Throwable, message: String) {
        with(throwable).log(message, E)
    }

    @JvmStatic
    fun i(message: String) {
        LogEntry().log(message, I)
    }

    @JvmStatic
    fun i(throwable: Throwable, message: String) {
        with(throwable).log(message, I)
    }

    @JvmStatic
    fun v(message: String) {
        LogEntry().log(message, V)
    }

    @JvmStatic
    fun v(throwable: Throwable, message: String) {
        with(throwable).log(message, V)
    }

    @JvmStatic
    fun w(message: String) {
        LogEntry().log(message, W)
    }

    @JvmStatic
    fun w(throwable: Throwable, message: String) {
        with(throwable).log(message, W)
    }

    @JvmStatic
    fun with(key: String, value: Any?): LogEntry {
        return LogEntry().with(key, value)
    }

    @JvmStatic
    fun tag(tag: String): LogEntry {
        return LogEntry().tag(tag)
    }

    @JvmStatic
    fun with(error: Throwable): LogEntry {
        return LogEntry().with(error)
    }

    @JvmStatic
    fun addPoeg(consumer: Kalevipoeg) {
        consumers.add(consumer)
    }

    internal fun log(entry: LogEntry) {
        consumers.forEach {
            it.log(entry)
        }
    }
}

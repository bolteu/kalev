package eu.bolt.kalev

import eu.bolt.kalev.fast.FastLog

@Suppress("MemberVisibilityCanBePrivate", "unused")
object Kalev {

    const val V = "v"
    const val D = "d"
    const val I = "i"
    const val W = "w"
    const val E = "e"

    /**
     * No Operation mode.
     * In case of enabled NOP mode Kalev will not create log entries and dispatch them to consumers
     */
    var nop = false
    private val nopEntry = NopLogEntry

    /**
     * FastLog can be used for performance-sensitive logging. Note that FastLog will NOT trigger log consumers
     */
    var fastLog: FastLog? = null

    private val consumers = mutableListOf<Kalevipoeg>()

    @JvmStatic
    fun d(message: String) {
        getEntry().log(message, D)
    }

    @JvmStatic
    fun d(throwable: Throwable, message: String) {
        with(throwable).log(message, D)
    }

    @JvmStatic
    fun e(message: String) {
        getEntry().log(message, E)
    }

    @JvmStatic
    fun e(throwable: Throwable, message: String) {
        with(throwable).log(message, E)
    }

    @JvmStatic
    fun i(message: String) {
        getEntry().log(message, I)
    }

    @JvmStatic
    fun i(throwable: Throwable, message: String) {
        with(throwable).log(message, I)
    }

    @JvmStatic
    fun v(message: String) {
        getEntry().log(message, V)
    }

    @JvmStatic
    fun v(throwable: Throwable, message: String) {
        with(throwable).log(message, V)
    }

    @JvmStatic
    fun w(message: String) {
        getEntry().log(message, W)
    }

    @JvmStatic
    fun w(throwable: Throwable, message: String) {
        with(throwable).log(message, W)
    }

    @JvmStatic
    fun with(key: String, value: Any?): LogEntry {
        return getEntry().with(key, value)
    }

    @JvmStatic
    fun tag(tag: String): LogEntry {
        return getEntry().tag(tag)
    }

    @JvmStatic
    fun with(error: Throwable): LogEntry {
        return getEntry().with(error)
    }

    @JvmStatic
    fun addPoeg(consumer: Kalevipoeg) {
        consumers.add(consumer)
    }

    @JvmStatic
    fun removePoeg(consumer: Kalevipoeg) {
        consumers.remove(consumer)
    }

    @JvmStatic
    fun removeAllPoegs() {
        consumers.clear()
    }

    fun getEntry(): LogEntry {
        return if (nop) {
            nopEntry
        } else {
            LogEntry()
        }
    }

    internal fun log(entry: LogEntry) {
        consumers.forEach {
            it.log(entry)
        }
    }
}

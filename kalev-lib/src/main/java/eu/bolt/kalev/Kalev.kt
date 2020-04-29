package eu.bolt.kalev

import sun.rmi.runtime.Log

object Kalev {

    const val V = "v"
    const val D = "d"
    const val I = "i"
    const val W = "w"
    const val E = "e"

    private val consumers = mutableListOf<Kalevipoeg>()

    @JvmStatic
    fun d(message: String) {
        LogEntry().d(message)
    }

    @JvmStatic
    fun w(message: String) {
        LogEntry().w(message)
    }

    @JvmStatic
    fun w(throwable: Throwable) {
        LogEntry().with(throwable).w("")
    }

    @JvmStatic
    fun with(key: String, value: String): LogEntry {
        return LogEntry().with(key, value)
    }

    @JvmStatic
    fun with(key: String, value: Int): LogEntry {
        return LogEntry().with(key, value)
    }

    fun with(key: String, value: Any?): LogEntry {
        return LogEntry().with(key, value)
    }

    fun with(key: String, value: Boolean): LogEntry {
        return LogEntry().with(key, value)
    }

    fun tag(tag: String): LogEntry {
        return LogEntry().tag(tag)
    }

    fun with(error: Throwable): LogEntry {
        return LogEntry().with(error)
    }

    fun addPoeg(consumer: Kalevipoeg) {
        consumers.add(consumer)
    }

    internal fun log(entry: LogEntry) {
        consumers.forEach {
            it.log(entry)
        }
    }
}

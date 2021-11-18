package eu.bolt.kalev

import eu.bolt.kalev.fast.FastLog
import eu.bolt.kalev.logger.EntryProvider
import eu.bolt.kalev.logger.Logger
import eu.bolt.kalev.logger.simple.SimpleEntryFactory
import eu.bolt.kalev.logger.impl.EntryFactory
import eu.bolt.kalev.logger.nop.NoOpProvider

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
    val defaultLogger = Logger(EntryFactory)
    val noOpLogger = Logger(NoOpProvider)
    val fastLogger = Logger(SimpleEntryFactory)
    private var logger = Logger(EntryFactory)

    /**
     * FastLog can be used for performance-sensitive logging. Note that FastLog will NOT trigger log consumers
     */
    var fastLog: FastLog? = null

    private val consumers = mutableListOf<Kalevipoeg>()

    fun setLogger(provider: EntryProvider) {
        logger = Logger(provider)
    }

    fun getLogger(): Logger {
        return logger
    }

    @JvmStatic
    fun d(message: String) {
        getLogger().log(message, D)
    }

    @JvmStatic
    fun e(throwable: Throwable, message: String) {
        with(throwable).log(message, E)
    }

    @JvmStatic
    fun i(message: String) {
        getLogger().log(message, I)
    }

    @JvmStatic
    fun v(throwable: Throwable, message: String) {
        with(throwable).log(message, V)
    }

    @JvmStatic
    fun w(message: String) {
        getLogger().log(message, W)
    }

    @JvmStatic
    fun w(throwable: Throwable, message: String) {
        with(throwable).log(message, W)
    }

    @JvmStatic
    fun e(error: Throwable) {
        getLogger().log(error.message ?: error.javaClass.simpleName, E)
    }

    @JvmStatic
    fun with(key: String, value: Any?): LogEntry {
        return getLogger().with(key, value)
    }

    @JvmStatic
    fun tag(tag: String): LogEntry {
        return getLogger().tag(tag)
    }

    @JvmStatic
    fun with(error: Throwable): LogEntry {
        return getLogger().with(error)
    }

    @JvmStatic
    fun addPoeg(consumer: Kalevipoeg) {
        consumers.add(consumer)
    }

    @JvmStatic
    fun removePoeg(consumer: Kalevipoeg) {
        consumers.remove(consumer)
    }

    internal fun dispatch(entry: LogEntry) {
        consumers.forEach {
            it.log(entry)
        }
    }
}

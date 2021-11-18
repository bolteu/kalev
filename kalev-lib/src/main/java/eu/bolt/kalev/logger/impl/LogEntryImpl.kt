package eu.bolt.kalev.logger.impl

import eu.bolt.kalev.Kalev
import eu.bolt.kalev.LogEntry
import java.util.Date

@Suppress("unused")
class LogEntryImpl internal constructor() : LogEntry {

    override var tag: String? = null
        private set
    override var throwable: Throwable? = null
        private set
    var timestamp: Long? = null
        private set
    override lateinit var severity: String
    lateinit var message: String
    val data = mutableMapOf<String, Any?>()

    override fun with(key: String, value: Any?): LogEntry {
        data[key] = value
        return this
    }

    override fun with(throwable: Throwable): LogEntry {
        this.throwable = throwable
        return this
    }

    override fun tag(tag: String): LogEntry {
        this.tag = tag
        return this
    }

    override fun v(message: String) {
        log(message, Kalev.V)
    }

    override fun d(message: String) {
        log(message, Kalev.D)
    }

    override fun i(message: String) {
        log(message, Kalev.I)
    }

    override fun w(message: String) {
        log(message, Kalev.W)
    }

    override fun e(message: String) {
        log(message, Kalev.E)
    }

    override fun log(message: String, severity: String) {
        this.severity = severity
        timestamp = Date().time
        this.message = message
        Kalev.dispatch(this)
    }

    override fun toString(): String {
        return "LogEntry(tag=$tag, throwable=$throwable, timestamp=$timestamp, severity='$severity', message='$message', data=$data)"
    }
}

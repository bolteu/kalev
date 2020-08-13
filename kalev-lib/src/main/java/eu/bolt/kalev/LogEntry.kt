package eu.bolt.kalev

import java.util.Date

@Suppress("unused")
open class LogEntry internal constructor() {

    var tag: String? = null
        private set
    var throwable: Throwable? = null
        private set
    var timestamp: Long? = null
        private set
    lateinit var severity: String
    lateinit var message: String
    val data = mutableMapOf<String, Any?>()

    open fun with(key: String, value: Any?): LogEntry {
        data[key] = value
        return this
    }

    open fun with(throwable: Throwable): LogEntry {
        this.throwable = throwable
        return this
    }

    open fun tag(tag: String): LogEntry {
        this.tag = tag
        return this
    }

    open fun v(message: String) {
        log(message, Kalev.V)
    }

    open fun d(message: String) {
        log(message, Kalev.D)
    }

    open fun i(message: String) {
        log(message, Kalev.I)
    }

    open fun w(message: String) {
        log(message, Kalev.W)
    }

    open fun e(message: String) {
        log(message, Kalev.E)
    }

    internal open fun log(message: String, severity: String) {
        this.severity = severity
        timestamp = Date().time
        this.message = message
        Kalev.log(this)
    }

    override fun toString(): String {
        return "LogEntry(tag=$tag, throwable=$throwable, timestamp=$timestamp, severity='$severity', message='$message', data=$data)"
    }
}

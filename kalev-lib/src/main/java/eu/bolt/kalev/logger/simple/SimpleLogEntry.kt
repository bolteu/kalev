package eu.bolt.kalev.logger.simple

import eu.bolt.kalev.Kalev
import eu.bolt.kalev.LogEntry

class SimpleLogEntry : LogEntry {

    lateinit var message: String
        private set
    override lateinit var severity: String
        private set
    override var tag: String? = null
        private set
    override var throwable: Throwable? = null
        private set

    override fun with(key: String, value: Any?): LogEntry {
        throw UnsupportedOperationException()
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
        this.message = message
        Kalev.dispatch(this)
    }
}
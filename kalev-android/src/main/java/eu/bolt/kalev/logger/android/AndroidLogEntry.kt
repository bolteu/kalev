package eu.bolt.kalev.logger.android

import android.util.Log
import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.utils.generateTag

class AndroidLogEntry : LogEntry {

    override fun with(key: String, value: Any?): LogEntry {
        return this
    }

    override fun with(throwable: Throwable): LogEntry {
        return this
    }

    override fun tag(tag: String): LogEntry {
        return this
    }

    override fun v(message: String) {
        Log.v(tag, message)
    }

    override fun d(message: String) {
        Log.d(tag, message)
    }

    override fun i(message: String) {
        Log.i(tag, message)
    }

    override fun w(message: String) {
        Log.w(tag, message)
    }

    override fun e(message: String) {
        Log.e(tag, message)
    }

    override fun log(message: String, severity: String) {
        Log.println(Log.INFO, tag, message)
    }

    override val severity: String
        get() {
            throw UnsupportedOperationException()
        }

    override val throwable: Throwable?
        get() {
            throw UnsupportedOperationException()
        }

    override val tag: String?
        get() = generateTag(3)
}

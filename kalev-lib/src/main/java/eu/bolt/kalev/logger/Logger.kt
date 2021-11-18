package eu.bolt.kalev.logger

import eu.bolt.kalev.Kalev
import eu.bolt.kalev.LogEntry

class Logger (
    private val entryProvider: EntryProvider
) {

    fun v(message: String) {
        entryProvider.getEntry().log(message, Kalev.V)
    }

    fun d(message: String) {
        entryProvider.getEntry().log(message, Kalev.D)
    }

    fun i(message: String) {
        entryProvider.getEntry().log(message, Kalev.I)
    }

    fun w(message: String) {
        entryProvider.getEntry().log(message, Kalev.W)
    }

    fun e(message: String) {
        entryProvider.getEntry().log(message, Kalev.E)
    }

    fun e(error: Throwable, message: String) {
        entryProvider.getEntry().with(error).log(message, Kalev.E)
    }

    fun e(error: Throwable) {
        entryProvider.getEntry().log(error.message ?: error.javaClass.simpleName, Kalev.E)
    }

    fun tag(tag: String): LogEntry {
        return entryProvider.getEntry().tag(tag)
    }

    fun with(error: Throwable): LogEntry {
        return entryProvider.getEntry().with(error)
    }

    fun with(key: String, value: Any?): LogEntry {
        return entryProvider.getEntry().with(key, value)
    }

    fun log(message: String, severity: String) {
        entryProvider.getEntry().log(message, severity)
    }
}

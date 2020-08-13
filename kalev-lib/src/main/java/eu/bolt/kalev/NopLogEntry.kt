package eu.bolt.kalev

object NopLogEntry : LogEntry() {

    override fun with(key: String, value: Any?): LogEntry {
        return this
    }

    override fun with(throwable: Throwable): LogEntry {
        return this
    }

    override fun tag(tag: String): LogEntry {
        return this
    }

    override fun log(message: String, severity: String) {
        // do nothing
    }
}

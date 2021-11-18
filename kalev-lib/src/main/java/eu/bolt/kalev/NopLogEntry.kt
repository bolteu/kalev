package eu.bolt.kalev

object NopLogEntry : LogEntry {

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
        //do nothing
    }

    override fun d(message: String) {
        //do nothing
    }

    override fun i(message: String) {
        //do nothing
    }

    override fun w(message: String) {
        //do nothing
    }

    override fun e(message: String) {
        //do nothing
    }

    override fun log(message: String, severity: String) {
        //do nothing
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
        get() {
            throw UnsupportedOperationException()
        }
}

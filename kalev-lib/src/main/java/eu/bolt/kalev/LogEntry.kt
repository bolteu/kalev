package eu.bolt.kalev

interface LogEntry {

    fun with(key: String, value: Any?): LogEntry

    fun with(throwable: Throwable): LogEntry

    fun tag(tag: String): LogEntry

    fun v(message: String)

    fun d(message: String)

    fun i(message: String)

    fun w(message: String)

    fun e(message: String)

    fun log(message: String, severity: String)

    val severity:String

    val throwable: Throwable?

    val tag: String?
}

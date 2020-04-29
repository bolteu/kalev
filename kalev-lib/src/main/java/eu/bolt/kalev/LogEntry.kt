package eu.bolt.kalev

import org.json.JSONObject
import java.util.Date

class LogEntry {

    var tag: String? = null
        private set
    var throwable: Throwable? = null
        private set
    private val data = mutableMapOf<String, Any>()
    lateinit var severity: String
    lateinit var message: String
    var timestamp: Long? = null
        private set

    fun with(key: String, value: String): LogEntry {
        data[key] = value
        return this
    }

    fun with(key: String, value: Int): LogEntry {
        data[key] = value
        return this
    }

    fun with(key: String, value: Any?): LogEntry {
        data[key] = value ?: "null"
        return this
    }

    fun with(key: String, value: Boolean): LogEntry {
        data[key] = value
        return this
    }

    fun with(key: String, value: Long): LogEntry {
        data[key] = value
        return this
    }

    fun with(key: String, value: JSONObject): LogEntry {
        data[key] = value
        return this
    }

    fun with(throwable: Throwable): LogEntry {
        this.throwable = throwable
        return this
    }

    fun tag(tag: String): LogEntry {
        this.tag = tag
        return this
    }

    fun getTimestamp(): Long = checkNotNull(timestamp)

    fun getData(): Map<String, Any> = data

    fun getDataAsString(): String {
        val oobj = JSONObject()
        data.entries.forEach {
            oobj.put(it.key, it.value)
        }
        return oobj.toString()
    }

    fun v(message: String) {
        severity = Kalev.V
        timestamp = Date().time
        this.message = message
        Kalev.log(this)
    }

    fun d(message: String) {
        severity = Kalev.D
        timestamp = Date().time
        this.message = message
        Kalev.log(this)
    }

    fun i(message: String) {
        severity = Kalev.I
        timestamp = Date().time
        this.message = message
        Kalev.log(this)
    }

    fun w(message: String) {
        severity = Kalev.W
        timestamp = Date().time
        this.message = message
        Kalev.log(this)
    }

    fun e(message: String) {
        severity = Kalev.E
        timestamp = Date().time
        this.message = message
        Kalev.log(this)
    }

    override fun toString(): String {
        val oobj = JSONObject()
        oobj.put("message", message)
        oobj.put("tag", tag)
        data.entries.forEach {
            oobj.put(it.key, it.value)
        }
        return oobj.toString()
    }
}

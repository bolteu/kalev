package eu.bolt.kalev.fast

interface FastLog {

    fun v(message: String, tag: String? = null)

    fun d(message: String, tag: String? = null)

    fun i(message: String, tag: String? = null)
}

package eu.bolt.kalev.fast

/**
 * Default [FastLog] implementation which prints the logs to standard output
 */
class SystemFastLog : FastLog {

    override fun v(message: String, tag: String?) {
        print(message, tag)
    }

    override fun d(message: String, tag: String?) {
        print(message, tag)
    }

    override fun i(message: String, tag: String?) {
        print(message, tag)
    }

    private fun print(message: String, tag: String?) {
        if (tag == null) {
            println(message)
        } else {
            println(StringBuilder(tag).append(": ").append(message).toString())
        }
    }
}

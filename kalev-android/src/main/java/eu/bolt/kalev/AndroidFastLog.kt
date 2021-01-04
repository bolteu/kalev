package eu.bolt.kalev

import android.util.Log
import eu.bolt.kalev.fast.FastLog
import eu.bolt.kalev.utils.generateTag

class AndroidFastLog : FastLog {

    private val stackDepth = 3

    override fun v(message: String, tag: String?) {
        val finalTag = tag ?: generateTag(stackDepth)
        Log.v(finalTag, message)
    }

    override fun d(message: String, tag: String?) {
        val finalTag = tag ?: generateTag(stackDepth)
        Log.d(finalTag, message)
    }

    override fun i(message: String, tag: String?) {
        val finalTag = tag ?: generateTag(stackDepth)
        Log.i(finalTag, message)
    }
}

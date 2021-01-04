package eu.bolt.kalev

import android.util.Log
import eu.bolt.kalev.utils.generateTag
import org.json.JSONObject

class PrintPoeg : Kalevipoeg {

    private val callStackIndex = 6

    override fun log(entry: LogEntry) {
        when (entry.severity) {
            Kalev.V -> Log.v(getTag(entry), entry.formatString(), entry.throwable)
            Kalev.D -> Log.d(getTag(entry), entry.formatString(), entry.throwable)
            Kalev.I -> Log.i(getTag(entry), entry.formatString(), entry.throwable)
            Kalev.W -> Log.w(getTag(entry), entry.formatString(), entry.throwable)
            Kalev.E -> Log.e(getTag(entry), entry.formatString(), entry.throwable)
        }
    }

    private fun getTag(entry: LogEntry): String {
        return entry.tag ?: generateTag(callStackIndex)
    }

    private fun LogEntry.formatString(): String {
        val oobj = JSONObject()
        oobj.put("message", message)
        data.entries.forEach {
            oobj.put(it.key, it.value)
        }
        return oobj.toString()
    }
}

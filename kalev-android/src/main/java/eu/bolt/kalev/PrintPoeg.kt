package eu.bolt.kalev

import android.os.Build
import android.util.Log
import org.json.JSONObject
import java.util.regex.Pattern

class PrintPoeg : Kalevipoeg {

    private val anonymousClassPattern = Pattern.compile("(\\$\\d+)+$")
    private val maxTagLength = 23
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
        return entry.tag ?: createStackElementTag()
    }

    private fun createStackElementTag(): String {
        val stackTrace = Throwable().stackTrace
        check(stackTrace.size > callStackIndex) { "Synthetic stacktrace didn't have enough elements: are you using proguard?" }
        val element = stackTrace[callStackIndex]

        var tag = element.className
        val matcher = anonymousClassPattern.matcher(tag)
        if (matcher.find()) {
            tag = matcher.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)
        // Tag length limit was removed in API 24.
        return if (tag.length <= maxTagLength || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tag
        } else tag.substring(0, maxTagLength)
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

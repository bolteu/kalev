package eu.bolt.kalev.sample.logstash.sender

import android.util.Log
import eu.bolt.kalev.LogEntry
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Collections

class LogstashSender (
    val url: String
) {

    private val okHttpClient = OkHttpClient.Builder().build()

    private val networkCallback = object : Callback {

        override fun onResponse(call: Call, response: Response) {
            Log.v("LOGSTASH", "Sent")
        }

        override fun onFailure(call: Call, e: IOException) {
            Log.v("LOGSTASH", "Error")
            e.printStackTrace()
        }
    }

    fun sendAsync(entry: LogEntry) {
        val request: Request = prepareRequest(Collections.singletonList(entry))

        okHttpClient.newCall(request).enqueue(networkCallback)
    }

    fun sendSync(entries: List<LogEntry>) {
        val request: Request = prepareRequest(entries)

        okHttpClient.newCall(request).execute()
    }

    private fun prepareRequest(entries: List<LogEntry>): Request {
        val body = entries.map { entry ->
            val json = JSONObject()
            json.put("message", entry.message)
            json.put("created", entry.timestamp)
            json.put("tag", entry.tag)
            json.put("level", entry.severity)
            json.put("error", entry.throwable?.message)
            entry.data.entries.forEach {
                if (it.key == "response.body") {
                    json.put("response_body", it.value.toString())
                } else {
                    json.put(it.key, it.value)
                }
            }
            return@map json
        }.joinToString(separator = "\n") { it.toString() }

        return Request.Builder()
            .url(url)
            .post(RequestBody.create(MediaType.get("application/json"), body))
            .build()
    }
}

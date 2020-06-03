package eu.bolt.kalev.okhttp

import eu.bolt.kalev.Kalev
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            logError(request ,e)
            throw e
        }
        logResponse(response)
        return response
    }

    private fun logResponse(response: Response) {
        val url = response.request().url()
        val query = url.queryParameterNames().map { name -> name to url.queryParameter(name) }.toMap()

        val entry = Kalev.with("method", response.request().method())
            .with("path", url.encodedPath())

        query.entries.forEach { queryEntry ->
            entry.with(queryEntry.key, queryEntry.value ?: "")
        }

        formatRequestBody(response.request())?.let {
            try {
                entry.with("request.body", JSONObject(it))
            } catch (exc: JSONException) {
                entry.with("request.body", it)
            }
        }

        entry.with("response.code", response.code())
        formatResponseBody(response.body())?.let {
            try {
                entry.with("response.body", JSONObject(it))
            } catch (exc: JSONException) {
                entry.with("response.body", it)
            }
        }
        entry.v(NETWORK_MESSAGE)
    }

    private fun formatRequestBody(request: Request): String? {
        if (request.method() == "GET") {
            return null
        }
        return request.body()?.let {
            val buffer = Buffer()
            it.writeTo(buffer)
            buffer.readString(Charset.forName("UTF-8"))
        }
    }

    private fun formatResponseBody(body: ResponseBody?): String? {
        return body?.let {
            val source = it.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            buffer.clone().readString(Charset.forName("UTF-8"))
        }
    }

    private fun logError(request: Request, error: Throwable) {
        val url = request.url()
        val query = url.queryParameterNames().map { name -> name to url.queryParameter(name) }.toMap()

        val entry = Kalev.with("method", request.method())
            .with("path", url.encodedPath())

        query.entries.forEach { queryEntry ->
            entry.with(queryEntry.key, queryEntry.value ?: "")
        }

        formatRequestBody(request)?.let {
            try {
                entry.with("request.body", JSONObject(it))
            } catch (exc: JSONException) {
                entry.with("request.body", it)
            }
        }

        entry.with("response.error", error.javaClass.name)
        error.message?.let {
            entry.with("response.error.message", it)
        }
        entry.e(NETWORK_MESSAGE)
    }

    companion object {
        const val NETWORK_MESSAGE = "network"
    }
}

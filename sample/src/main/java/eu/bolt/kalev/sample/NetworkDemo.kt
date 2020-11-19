package eu.bolt.kalev.sample

import android.app.Application
import android.util.Log
import eu.bolt.kalev.Kalev
import eu.bolt.kalev.okhttp.LoggingInterceptor
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkDemo (val application: Application) {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(LoggingInterceptor())
        .build()

    fun doGet() {
        val request: Request = Request.Builder()
            .url("https://httpbin.org/get?id=42")
            .header("Content-Type", "application/json")
            .build()

        okHttpClient.newCall(request).enqueue(networkCallback)
    }

    fun doNotFound() {
        val request: Request = Request.Builder()
            .url("https://httpbin.org/unknown")
            .header("Content-Type", "application/json")
            .build()

        okHttpClient.newCall(request).enqueue(networkCallback)
    }

    private val networkCallback = object : Callback {

        override fun onResponse(call: Call, response: Response) {
            Kalev.d("Got response")
            Log.e("Hello", "1M inserted/ ${(application as SampleApp).size} . 1")
        }

        override fun onFailure(call: Call, e: IOException) {
            Kalev.e(e, "Got error")
        }
    }
}
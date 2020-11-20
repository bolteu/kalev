package eu.bolt.kalev.sample

import android.app.Application
import eu.bolt.kalev.Kalev
import eu.bolt.kalev.PrintPoeg
import eu.bolt.kalev.sample.logstash.memory.MemoryQueueLogstashPoeg
import eu.bolt.kalev.sample.logstash.sender.LogstashSender

class SampleApp : Application() {

    private val listenerUrl = "YOUR-LISTENER-URL-HERE"

    override fun onCreate() {
        super.onCreate()

        val sender = LogstashSender(listenerUrl)

        if (BuildConfig.DEBUG) {
            Kalev.addPoeg(PrintPoeg())
        }

//        Kalev.addPoeg(LiveLogstashPoeg(sender))
//        Kalev.addPoeg(MemoryQueueLogstashPoeg(10, sender))
    }
}
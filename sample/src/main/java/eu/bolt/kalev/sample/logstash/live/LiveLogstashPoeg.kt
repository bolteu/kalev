package eu.bolt.kalev.sample.logstash.live

import eu.bolt.kalev.Kalevipoeg
import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.sample.logstash.sender.LogstashSender

/**
 * Immediately sends all logs to logstash. In case of any issues events will be lost.
 */
class LiveLogstashPoeg (
    private val sender: LogstashSender
) : Kalevipoeg {

    override fun log(entry: LogEntry) {
        sender.sendAsync(entry)
    }
}

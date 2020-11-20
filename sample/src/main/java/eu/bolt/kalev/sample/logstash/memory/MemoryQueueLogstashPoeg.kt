package eu.bolt.kalev.sample.logstash.memory

import android.util.Log
import eu.bolt.kalev.Kalevipoeg
import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.sample.logstash.sender.LogstashSender
import java.util.LinkedList
import java.util.concurrent.Executors

class MemoryQueueLogstashPoeg (
    private val flushThreshold: Int,
    private val sender: LogstashSender
) : Kalevipoeg {

    private val queue = LinkedList<LogEntry>()
    private val executor = Executors.newFixedThreadPool(1) {
        Thread(it, "LogExecuter")
    }

    override fun log(entry: LogEntry) {
        executor.submit { enqueue(entry) }
    }

    private fun enqueue(entry: LogEntry) {
        Log.v("LogQueue", "Enqueueing on ${Thread.currentThread().name}")
        queue.add(entry)
        if (queue.size > flushThreshold) {
            flush()
        }
    }

    private fun flush() {
        try {
            Log.v("LogQueue", "Flushing on ${Thread.currentThread().name}")
            sender.sendSync(queue)
            queue.clear()
            Log.v("LogQueue", "Flushed on ${Thread.currentThread().name}")
        } catch (thr: Throwable) {
            Log.e("LogQueue", "Failed to flush")
        }
    }
}

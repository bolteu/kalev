package eu.bolt.kalev.sample

import android.app.Application
import android.util.Log
import eu.bolt.kalev.Kalev
import eu.bolt.kalev.Kalevipoeg
import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.NopLogEntry
import eu.bolt.kalev.PrintPoeg
import eu.bolt.kalev.engine.default.DefaultEngine
import eu.bolt.kalev.engine.nop.NopEngine
import eu.bolt.kalev.engine.pool.PoolEngine

class SampleApp : Application() {

    val storage = ArrayList<LogEntry>()

    override fun onCreate() {
        super.onCreate()

        Kalev.engine = PoolEngine(10)

        if (BuildConfig.DEBUG) {
//            Kalev.addPoeg(PrintPoeg())

            Kalev.addPoeg(batcher)
        }
    }

    val size: Long
        get() {
            println("Batch: $storage")
            return storage.size.toLong()
        }

    private val batcher = object : Kalevipoeg {

        override fun log(entry: LogEntry) {
            synchronized(storage) {
                if (entry !is NopLogEntry) {
                    storage.add(entry)
                    if (storage.size == 250_000) {
                        storage.clear()
                        Log.e("Hello", "Cleared queue")
                    }
                }
            }
        }
    }
}

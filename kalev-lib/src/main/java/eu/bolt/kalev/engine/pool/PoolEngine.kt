package eu.bolt.kalev.engine.pool

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.NopLogEntry
import eu.bolt.kalev.engine.Engine
import java.util.LinkedList

class PoolEngine (val poolSize: Int) : Engine {

    private val idleObjects = LinkedList<LogEntry>()

    private val busyObjects = LinkedList<LogEntry>()

    override fun getEntry(): LogEntry {
        val candidate = idleObjects.pollFirst()
        println("Candidate: $candidate")
        if (candidate != null) {
            return candidate
        }
        println("Can instantiate: ${size()} - $poolSize")
        val canInstantiate = size() < poolSize
        if (canInstantiate) {
            val newEntry = PoolLogEntry() {
                busyObjects.remove(it)
                idleObjects.push(it)
            }
            busyObjects.add(newEntry)
            return newEntry
        }
        return NopLogEntry
    }

    private fun size(): Int = idleObjects.size + busyObjects.size
}

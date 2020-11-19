package eu.bolt.kalev.engine.pool

import eu.bolt.kalev.LogEntry

class PoolLogEntry (val recycleAction: (LogEntry) -> Unit) : LogEntry() {

    override fun log(message: String, severity: String) {
        super.log(message, severity)
        recycleAction(this)
    }
}

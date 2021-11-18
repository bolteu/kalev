package eu.bolt.kalev.logger.nop

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.NopLogEntry
import eu.bolt.kalev.logger.EntryProvider

object NoOpProvider : EntryProvider {

    override fun getEntry(): LogEntry {
        return NopLogEntry
    }
}

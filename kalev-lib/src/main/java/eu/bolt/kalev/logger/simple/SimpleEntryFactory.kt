package eu.bolt.kalev.logger.simple

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.logger.EntryProvider

object SimpleEntryFactory : EntryProvider {

    override fun getEntry(): LogEntry {
        return SimpleLogEntry()
    }
}

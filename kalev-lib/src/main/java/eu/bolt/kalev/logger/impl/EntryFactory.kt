package eu.bolt.kalev.logger.impl

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.logger.EntryProvider

object EntryFactory : EntryProvider {

    override fun getEntry(): LogEntry {
        return LogEntryImpl()
    }
}

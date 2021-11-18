package eu.bolt.kalev.logger.android

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.logger.EntryProvider

class AndroidLogFactory : EntryProvider {

    override fun getEntry(): LogEntry {
        return AndroidLogEntry()
    }
}

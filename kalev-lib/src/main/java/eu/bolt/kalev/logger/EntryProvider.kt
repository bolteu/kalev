package eu.bolt.kalev.logger

import eu.bolt.kalev.LogEntry

interface EntryProvider {

    fun getEntry(): LogEntry
}

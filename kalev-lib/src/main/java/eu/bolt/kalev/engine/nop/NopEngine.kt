package eu.bolt.kalev.engine.nop

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.NopLogEntry
import eu.bolt.kalev.engine.Engine

class NopEngine : Engine {

    override fun getEntry(): LogEntry = NopLogEntry
}

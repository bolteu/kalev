package eu.bolt.kalev.engine.default

import eu.bolt.kalev.LogEntry
import eu.bolt.kalev.engine.Engine

class DefaultEngine : Engine {

    override fun getEntry(): LogEntry = LogEntry()
}

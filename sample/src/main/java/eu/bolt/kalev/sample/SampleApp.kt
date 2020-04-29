package eu.bolt.kalev.sample

import android.app.Application
import eu.bolt.kalev.Kalev
import eu.bolt.kalev.PrintPoeg

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Kalev.addPoeg(PrintPoeg())
        }
    }
}
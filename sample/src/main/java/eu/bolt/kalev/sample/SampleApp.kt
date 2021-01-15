package eu.bolt.kalev.sample

import android.app.Application
import eu.bolt.kalev.AndroidFastLog
import eu.bolt.kalev.Kalev
import eu.bolt.kalev.PrintPoeg
import eu.bolt.kalev.fast.SystemFastLog

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Kalev.addPoeg(PrintPoeg())
            Kalev.fastLog = AndroidFastLog()
        }
    }
}
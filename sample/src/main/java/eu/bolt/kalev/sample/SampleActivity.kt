package eu.bolt.kalev.sample

import android.app.Activity
import android.os.Bundle
import android.view.View
import eu.bolt.kalev.Kalev

class SampleActivity : Activity() {

    private var clickCounter = 0
    private val networkDemo = NetworkDemo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity)

        findViewById<View>(R.id.click).setOnClickListener {
            clickCounter += 1
            Kalev.with("counter", clickCounter).d("Click")
            Kalev.fastLog?.d("Click")
        }

        findViewById<View>(R.id.networkGet).setOnClickListener {
            networkDemo.doGet()
            Kalev.fastLog?.d("Started network request")
        }

        findViewById<View>(R.id.networkErr).setOnClickListener {
            networkDemo.doNotFound()
            Kalev.fastLog?.d("Started network request")
        }

        Kalev.d("On start")
        Kalev.fastLog?.v("Activity created")
    }
}

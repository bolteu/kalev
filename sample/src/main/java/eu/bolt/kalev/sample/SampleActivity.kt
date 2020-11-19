package eu.bolt.kalev.sample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import eu.bolt.kalev.Kalev

class SampleActivity : Activity() {

    private var clickCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity)

        findViewById<View>(R.id.click).setOnClickListener {
            clickCounter += 1
//            Kalev.with("counter", clickCounter).d("Click")

            for(i in 1..15) {
                val entry = Kalev.with("i", i)
                Log.e("Hello", "Entry: $entry")
                entry.d("Counter increased")
            }

            Log.e("Hello", "1M inserted/ ${(application as SampleApp).size}")
        }

        val networkDemo = NetworkDemo(application)

        findViewById<View>(R.id.networkGet).setOnClickListener {
            networkDemo.doGet()
        }

        findViewById<View>(R.id.networkErr).setOnClickListener {
            networkDemo.doNotFound()
        }

        Kalev.d("On start")
    }
}

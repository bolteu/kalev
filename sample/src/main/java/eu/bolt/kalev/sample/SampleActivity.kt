package eu.bolt.kalev.sample

import android.app.Activity
import android.os.Bundle
import android.view.View
import eu.bolt.kalev.Kalev

class SampleActivity : Activity() {

    private var clickCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_activity)

        findViewById<View>(R.id.click).setOnClickListener {
            clickCounter += 1
            Kalev.with("counter", clickCounter).d("Click")
        }

        Kalev.d("On start")
    }
}

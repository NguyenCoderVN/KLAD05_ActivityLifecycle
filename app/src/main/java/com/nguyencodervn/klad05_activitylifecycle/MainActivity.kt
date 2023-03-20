package com.nguyencodervn.klad05_activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    private lateinit var start: Button
    private lateinit var pause: Button
    private lateinit var stop: Button
    private lateinit var chronometer: Chronometer
    private var running: Boolean = false
    private var timeElapse: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: ")
        start = findViewById(R.id.startBt)
        pause = findViewById(R.id.pauseBt)
        stop = findViewById(R.id.stopBt)
        chronometer = findViewById(R.id.chronometer2)

        if (savedInstanceState!=null) {
            timeElapse = savedInstanceState.getLong(TIME_ELAPSE_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running) {
                chronometer.base = savedInstanceState.getLong(BASE_KEY)
                chronometer.start()
            } else setBaseTime()
        }

        start.setOnClickListener {
            if(!running) {
                setBaseTime()
                chronometer.start()
                running = true
            }
        }

        pause.setOnClickListener {
            if (running) {
                saveTimeElapse()
                chronometer.stop()
                running = false
            }
        }

        stop.setOnClickListener {
            timeElapse = 0
            setBaseTime()
            chronometer.stop()
            running = false
        }

    }

    private fun setBaseTime() {
        chronometer.base = SystemClock.elapsedRealtime() - timeElapse
    }

    private fun saveTimeElapse() {
        timeElapse = SystemClock.elapsedRealtime() - chronometer.base
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(TIME_ELAPSE_KEY, timeElapse)
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(BASE_KEY, chronometer.base)
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    companion object {
        const val TAG = "MYTAG"
        const val TIME_ELAPSE_KEY = "TIME_ELAPSE_KEY"
        const val RUNNING_KEY = "RUNNING_KEY"
        const val BASE_KEY = "BASE_KEY"
    }
}


package com.example.tracker

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    internal var stepCount: Int = 0

    private lateinit var stepText: TextView
    private lateinit var historyButton: androidx.appcompat.widget.AppCompatButton
    private lateinit var settingsButton: androidx.appcompat.widget.AppCompatButton

    private val prefsName = "StepTrackerPrefs"
    private val keySteps = "steps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stepText = findViewById(R.id.stepText)
        historyButton = findViewById(R.id.historyButton)
        settingsButton = findViewById(R.id.settingsButton)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        stepCount = loadSteps()
        stepText.text = "Steps: $stepCount"

        historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        stepSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        saveSteps()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = it.values[0]
            val y = it.values[1]
            val z = it.values[2]

            val magnitude = Math.sqrt((x*x + y*y + z*z).toDouble())
            if (magnitude > 12) {
                stepCount++
                stepText.text = "Steps: $stepCount"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun saveSteps() {
        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val editor = prefs.edit()


        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val historyKey = "history_$date"
        editor.putInt(historyKey, stepCount)
        editor.apply()
    }

    private fun loadSteps(): Int {
        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val historyKey = "history_$date"
        return prefs.getInt(historyKey, 0)
    }
}
package com.example.tracker

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoryActivity : AppCompatActivity() {
    private lateinit var historyText: TextView
    private val prefsName = "StepTrackerPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyText = findViewById(R.id.historyText)
        showHistory()
    }

    private fun showHistory() {
        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val allEntries = prefs.all


        val historyEntries = allEntries.filter { it.key.startsWith("history_") }
            .toSortedMap(compareByDescending { it })

        val sb = StringBuilder()
        for ((dateKey, steps) in historyEntries)
        {
            val date = dateKey.removePrefix("history_")
            sb.append("$date: $steps steps\n")
        }

        if (sb.isEmpty())
        {
            sb.append("No history yet")
        }

        historyText.text = sb.toString()
    }
}
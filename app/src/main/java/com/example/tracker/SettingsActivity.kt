package com.example.tracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private lateinit var resetButton: androidx.appcompat.widget.AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)
        resetButton = findViewById(R.id.resetButton)

        resetButton.setOnClickListener {


            MainActivity().stepCount = 0
        }
    }
}
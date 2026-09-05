package com.bloodmoon.launcher

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        playButton.setOnClickListener {
            Toast.makeText(
                this,
                "Запуск Minecraft 1.16.5...",
                Toast.LENGTH_SHORT
            ).show()
        }

        settingsButton.setOnClickListener {
            Toast.makeText(
                this,
                "Настройки скоро будут доступны",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

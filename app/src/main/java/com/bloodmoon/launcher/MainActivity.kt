package com.bloodmoon.launcher

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var moonPulse: ObjectAnimator? = null
    private var moonFloat: ObjectAnimator? = null
    private var buttonPulse: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val moon = findViewById<ImageView>(R.id.bloodMoon)
        val playButton = findViewById<Button>(R.id.playButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        // 🌑 Пульсация кровавой Луны
        moonPulse = ObjectAnimator.ofFloat(
            moon,
            "alpha",
            0.82f,
            1.0f,
            0.82f
        ).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        // 🌑 Плавное движение Луны
        moonFloat = ObjectAnimator.ofFloat(
            moon,
            "translationY",
            -6f,
            6f,
            -6f
        ).apply {
            duration = 5000
            repeatCount = ValueAnimator.INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        // 🔴 Пульсация кнопки ИГРАТЬ
        buttonPulse = ObjectAnimator.ofPropertyValuesHolder(
            playButton,
            PropertyValuesHolder.ofFloat(
                "scaleX",
                1.0f,
                1.025f,
                1.0f
            ),
            PropertyValuesHolder.ofFloat(
                "scaleY",
                1.0f,
                1.025f,
                1.0f
            )
        ).apply {
            duration = 1800
            repeatCount = ValueAnimator.INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        // ▶ ИГРАТЬ
        playButton.setOnClickListener {

            Toast.makeText(
                this,
                "Запуск Minecraft 1.16.5...",
                Toast.LENGTH_SHORT
            ).show()
        }

        // ⚙ НАСТРОЙКИ
        settingsButton.setOnClickListener {

            val intent = Intent(
                this,
                SettingsActivity::class.java
            )

            startActivity(intent)
        }
    }

    override fun onDestroy() {

        moonPulse?.cancel()
        moonFloat?.cancel()
        buttonPulse?.cancel()

        super.onDestroy()
    }
}

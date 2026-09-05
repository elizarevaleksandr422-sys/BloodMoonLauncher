package com.bloodmoon.launcher

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val moon = findViewById<ImageView>(R.id.bloodMoon)
        val playButton = findViewById<Button>(R.id.playButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        // Плавное пульсирование кровавой Луны
        val pulse = ObjectAnimator.ofFloat(
            moon,
            "alpha",
            0.82f,
            1.0f,
            0.82f
        )

        pulse.duration = 3000
        pulse.repeatCount = ValueAnimator.INFINITE
        pulse.interpolator = AccelerateDecelerateInterpolator()
        pulse.start()

        // Небольшое медленное движение Луны
        val floatAnimation = ObjectAnimator.ofFloat(
            moon,
            "translationY",
            -6f,
            6f,
            -6f
        )

        floatAnimation.duration = 5000
        floatAnimation.repeatCount = ValueAnimator.INFINITE
        floatAnimation.interpolator = AccelerateDecelerateInterpolator()
        floatAnimation.start()

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

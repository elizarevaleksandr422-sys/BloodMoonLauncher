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

        // 🌑 Пульсация Луны
        val moonPulse = ObjectAnimator.ofFloat(
            moon,
            "alpha",
            0.82f,
            1.0f,
            0.82f
        )

        moonPulse.duration = 3000
        moonPulse.repeatCount = ValueAnimator.INFINITE
        moonPulse.interpolator = AccelerateDecelerateInterpolator()
        moonPulse.start()

        // 🌑 Медленное движение Луны
        val moonFloat = ObjectAnimator.ofFloat(
            moon,
            "translationY",
            -6f,
            6f,
            -6f
        )

        moonFloat.duration = 5000
        moonFloat.repeatCount = ValueAnimator.INFINITE
        moonFloat.interpolator = AccelerateDecelerateInterpolator()
        moonFloat.start()

        // 🔴 Пульсация кнопки ИГРАТЬ
        val buttonPulse = ObjectAnimator.ofPropertyValuesHolder(
            playButton,
            android.animation.PropertyValuesHolder.ofFloat(
                "scaleX",
                1.0f,
                1.03f,
                1.0f
            ),
            android.animation.PropertyValuesHolder.ofFloat(
                "scaleY",
                1.0f,
                1.03f,
                1.0f
            ),
            android.animation.PropertyValuesHolder.ofFloat(
                "alpha",
                0.88f,
                1.0f,
                0.88f
            )
        )

        buttonPulse.duration = 1800
        buttonPulse.repeatCount = ValueAnimator.INFINITE
        buttonPulse.interpolator = AccelerateDecelerateInterpolator()
        buttonPulse.start()

        // ▶️ Нажатие ИГРАТЬ
        playButton.setOnClickListener {

            playButton.animate()
                .scaleX(0.94f)
                .scaleY(0.94f)
                .setDuration(100)
                .withEndAction {

                    playButton.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(150)
                        .start()

                    Toast.makeText(
                        this,
                        "Запуск Minecraft 1.16.5...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .start()
        }

        // ⚙️ Настройки
        settingsButton.setOnClickListener {

            Toast.makeText(
                this,
                "Настройки BloodMoon",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

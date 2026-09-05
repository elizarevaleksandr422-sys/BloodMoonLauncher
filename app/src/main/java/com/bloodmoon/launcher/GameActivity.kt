package com.bloodmoon.launcher

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(
            "bloodmoon_settings",
            MODE_PRIVATE
        )

        val renderer = prefs.getString(
            "renderer",
            "Holy GL4ES"
        ) ?: "Holy GL4ES"

        val ram = prefs.getInt(
            "ram",
            2
        )

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.setPadding(24, 30, 24, 24)

        root.setBackgroundColor(
            Color.rgb(5, 3, 8)
        )

        // Заголовок

        val title = TextView(this)

        title.text = "BLOODMOON"
        title.textSize = 32f
        title.setTextColor(
            Color.rgb(215, 25, 53)
        )
        title.gravity = Gravity.CENTER

        root.addView(
            title,
            LinearLayout.LayoutParams(
                -1,
                65
            )
        )

        // Minecraft

        val version = TextView(this)

        version.text =
            "Minecraft Java Edition\n1.16.5"

        version.textSize = 18f
        version.setTextColor(Color.WHITE)
        version.gravity = Gravity.CENTER

        root.addView(
            version,
            LinearLayout.LayoutParams(
                -1,
                80
            )
        )

        // Renderer

        val rendererText = TextView(this)

        rendererText.text =
            "Renderer: $renderer"

        rendererText.textSize = 15f
        rendererText.setTextColor(
            Color.rgb(215, 25, 53)
        )
        rendererText.gravity = Gravity.CENTER

        root.addView(
            rendererText,
            LinearLayout.LayoutParams(
                -1,
                45
            )
        )

        // RAM

        val ramText = TextView(this)

        ramText.text =
            "RAM: $ram GB"

        ramText.textSize = 14f
        ramText.setTextColor(
            Color.rgb(160, 150, 155)
        )
        ramText.gravity = Gravity.CENTER

        root.addView(
            ramText,
            LinearLayout.LayoutParams(
                -1,
                40
            )
        )

        // Кнопка запуска

        val launchButton = Button(this)

        launchButton.text = "ЗАПУСТИТЬ MINECRAFT"
        launchButton.textSize = 15f
        launchButton.setTextColor(Color.WHITE)

        launchButton.setBackgroundColor(
            Color.rgb(160, 15, 40)
        )

        root.addView(
            launchButton,
            LinearLayout.LayoutParams(
                -1,
                60
            ).apply {
                topMargin = 20
            }
        )

        // Лог

        val logTitle = TextView(this)

        logTitle.text = "LAUNCH LOG"
        logTitle.textSize = 13f
        logTitle.setTextColor(
            Color.rgb(140, 125, 130)
        )

        root.addView(
            logTitle,
            LinearLayout.LayoutParams(
                -1,
                40
            ).apply {
                topMargin = 25
            }
        )

        val log = TextView(this)

        log.text =
            "> BloodMoon Launcher\n" +
            "> Minecraft 1.16.5\n" +
            "> Renderer: $renderer\n" +
            "> RAM: $ram GB\n" +
            "> Preparing Java Runtime...\n" +
            "> Preparing Minecraft...\n" +
            "> Waiting for runtime...\n"

        log.textSize = 12f
        log.setTextColor(
            Color.rgb(180, 170, 175)
        )

        val logScroll = ScrollView(this)

        logScroll.addView(log)

        root.addView(
            logScroll,
            LinearLayout.LayoutParams(
                -1,
                0,
                1f
            )
        )

        launchButton.setOnClickListener {

            log.append(
                "\n> Starting Minecraft...\n"
            )

            launchButton.isEnabled = false
            launchButton.text = "ЗАПУСК..."
        }

        setContentView(root)
    }
}

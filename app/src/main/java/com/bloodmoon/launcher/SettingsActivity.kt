package com.bloodmoon.launcher

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private val prefsName = "bloodmoon_settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )

        val scroll = ScrollView(this)

        scroll.setBackgroundColor(
            Color.rgb(5, 3, 8)
        )

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.setPadding(24, 30, 24, 30)

        scroll.addView(root)

        // =========================
        // ЗАГОЛОВОК
        // =========================

        val title = TextView(this)

        title.text = "BLOODMOON"
        title.textSize = 30f
        title.setTextColor(
            Color.rgb(215, 25, 53)
        )
        title.gravity = Gravity.CENTER

        root.addView(
            title,
            params(-1, 60)
        )

        val subtitle = TextView(this)

        subtitle.text = "НАСТРОЙКИ ЛАУНЧЕРА"
        subtitle.textSize = 12f
        subtitle.setTextColor(
            Color.rgb(150, 140, 145)
        )
        subtitle.gravity = Gravity.CENTER

        root.addView(
            subtitle,
            params(-1, 40)
        )

        // =========================
        // ПРОФИЛЬ
        // =========================

        root.addView(
            sectionTitle("ПРОФИЛЬ"),
            marginParams(45)
        )

        val nickname = EditText(this)

        nickname.hint = "Введите никнейм"
        nickname.textSize = 17f
        nickname.setSingleLine(true)

        nickname.setTextColor(Color.WHITE)
        nickname.setHintTextColor(
            Color.rgb(90, 80, 85)
        )

        nickname.setPadding(
            18,
            0,
            18,
            0
        )

        nickname.background = rounded(
            Color.rgb(25, 10, 17),
            16
        )

        nickname.setText(
            prefs.getString("nickname", "")
        )

        root.addView(
            nickname,
            marginParams(60)
        )

        // =========================
        // ОЗУ
        // =========================

        root.addView(
            sectionTitle("ОПЕРАТИВНАЯ ПАМЯТЬ"),
            marginParams(45)
        )

        val ramValue = TextView(this)

        val savedRam = prefs
            .getInt("ram", 2)
            .coerceIn(1, 8)

        ramValue.text = "$savedRam ГБ"
        ramValue.textSize = 22f
        ramValue.setTextColor(
            Color.rgb(215, 25, 53)
        )
        ramValue.gravity = Gravity.CENTER

        root.addView(
            ramValue,
            params(-1, 45)
        )

        val ramSeek = SeekBar(this)

        ramSeek.max = 7
        ramSeek.progress = savedRam - 1

        ramSeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                    val ram = progress + 1

                    ramValue.text = "$ram ГБ"

                    prefs.edit()
                        .putInt("ram", ram)
                        .apply()
                }

                override fun onStartTrackingTouch(
                    seekBar: SeekBar?
                ) {
                }

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {
                }
            }
        )

        root.addView(
            ramSeek,
            marginParams(55)
        )

        // =========================
        // RENDERER
        // =========================

        root.addView(
            sectionTitle("ГРАФИЧЕСКИЙ РЕНДЕРЕР"),
            marginParams(50)
        )

        val rendererValue = TextView(this)

        val savedRenderer = prefs.getString(
            "renderer",
            "Holy GL4ES"
        ) ?: "Holy GL4ES"

        rendererValue.text =
            "ТЕКУЩИЙ: $savedRenderer"

        rendererValue.textSize = 14f

        rendererValue.setTextColor(
            Color.rgb(215, 25, 53)
        )

        rendererValue.gravity = Gravity.CENTER

        root.addView(
            rendererValue,
            marginParams(45)
        )

        // Кнопки рендереров

        val renderers = listOf(
            "Holy GL4ES",
            "GL4ES",
            "Zink",
            "LTW"
        )

        for (renderer in renderers) {

            val button = Button(this)

            button.text = renderer
            button.textSize = 15f
            button.setTextColor(Color.WHITE)

            button.background = rounded(
                if (renderer == savedRenderer) {
                    Color.rgb(150, 12, 35)
                } else {
                    Color.rgb(30, 12, 20)
                },
                16
            )

            button.setOnClickListener {

                prefs.edit()
                    .putString(
                        "renderer",
                        renderer
                    )
                    .apply()

                rendererValue.text =
                    "ТЕКУЩИЙ: $renderer"

                Toast.makeText(
                    this,
                    "Рендерер: $renderer",
                    Toast.LENGTH_SHORT
                ).show()
            }

            root.addView(
                button,
                marginParams(58)
            )
        }

        // =========================
        // ВЕРСИЯ MINECRAFT
        // =========================

        root.addView(
            sectionTitle("ВЕРСИЯ ИГРЫ"),
            marginParams(50)
        )

        val version = TextView(this)

        version.text =
            "Minecraft Java Edition\n1.16.5"

        version.textSize = 18f
        version.setTextColor(Color.WHITE)
        version.gravity = Gravity.CENTER

        version.background = rounded(
            Color.rgb(18, 7, 13),
            16
        )

        root.addView(
            version,
            marginParams(85)
        )

        // =========================
        // СОХРАНИТЬ
        // =========================

        val save = Button(this)

        save.text = "СОХРАНИТЬ"
        save.textSize = 15f
        save.setTextColor(Color.WHITE)

        save.background = rounded(
            Color.rgb(170, 15, 42),
            16
        )

        save.setOnClickListener {

            prefs.edit()
                .putString(
                    "nickname",
                    nickname.text.toString().trim()
                )
                .apply()

            Toast.makeText(
                this,
                "Настройки сохранены",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }

        root.addView(
            save,
            marginParams(65)
        )

        // =========================
        // ВЕРСИЯ ЛАУНЧЕРА
        // =========================

        val launcherVersion = TextView(this)

        launcherVersion.text =
            "BLOODMOON LAUNCHER • v0.1"

        launcherVersion.textSize = 10f

        launcherVersion.setTextColor(
            Color.rgb(80, 65, 70)
        )

        launcherVersion.gravity = Gravity.CENTER

        root.addView(
            launcherVersion,
            marginParams(40)
        )

        setContentView(scroll)
    }

    private fun sectionTitle(
        text: String
    ): TextView {

        val view = TextView(this)

        view.text = text
        view.textSize = 13f

        view.setTextColor(
            Color.rgb(160, 150, 155)
        )

        return view
    }

    private fun params(
        width: Int,
        height: Int
    ): LinearLayout.LayoutParams {

        return LinearLayout.LayoutParams(
            width,
            height
        )
    }

    private fun marginParams(
        height: Int
    ): LinearLayout.LayoutParams {

        val params = LinearLayout.LayoutParams(
            -1,
            height
        )

        params.topMargin = 12

        return params
    }

    private fun rounded(
        color: Int,
        radius: Int
    ): GradientDrawable {

        val drawable = GradientDrawable()

        drawable.setColor(color)

        drawable.cornerRadius =
            radius.toFloat()

        return drawable
    }
}

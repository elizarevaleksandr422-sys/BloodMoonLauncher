package com.bloodmoon.launcher

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private val prefsName = "bloodmoon_settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )

        val scrollView = ScrollView(this)

        scrollView.setBackgroundColor(
            Color.rgb(5, 3, 8)
        )

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.setPadding(24, 30, 24, 30)

        scrollView.addView(root)

        // Заголовок
        val title = TextView(this)

        title.text = "BLOODMOON"
        title.textSize = 30f
        title.setTextColor(
            Color.rgb(215, 25, 53)
        )
        title.gravity = Gravity.CENTER

        root.addView(
            title,
            LinearLayout.LayoutParams(
                -1,
                60
            )
        )

        // Подзаголовок
        val subtitle = TextView(this)

        subtitle.text = "НАСТРОЙКИ ЛАУНЧЕРА"
        subtitle.textSize = 12f
        subtitle.setTextColor(
            Color.rgb(160, 150, 155)
        )
        subtitle.gravity = Gravity.CENTER

        root.addView(
            subtitle,
            LinearLayout.LayoutParams(
                -1,
                40
            )
        )

        // ПРОФИЛЬ
        val profileTitle = TextView(this)

        profileTitle.text = "ПРОФИЛЬ"
        profileTitle.textSize = 13f
        profileTitle.setTextColor(
            Color.rgb(160, 150, 155)
        )

        root.addView(
            profileTitle,
            marginParams(50)
        )

        val nickname = EditText(this)

        nickname.hint = "Введите никнейм"
        nickname.textSize = 17f
        nickname.setSingleLine(true)
        nickname.setTextColor(Color.WHITE)
        nickname.setHintTextColor(
            Color.rgb(100, 90, 95)
        )
        nickname.setPadding(18, 0, 18, 0)

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

        // ОЗУ
        val ramTitle = TextView(this)

        ramTitle.text = "ОПЕРАТИВНАЯ ПАМЯТЬ"
        ramTitle.textSize = 13f
        ramTitle.setTextColor(
            Color.rgb(160, 150, 155)
        )

        root.addView(
            ramTitle,
            marginParams(50)
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
            LinearLayout.LayoutParams(
                -1,
                45
            )
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

        // Версия Minecraft
        val gameTitle = TextView(this)

        gameTitle.text = "ВЕРСИЯ ИГРЫ"
        gameTitle.textSize = 13f
        gameTitle.setTextColor(
            Color.rgb(160, 150, 155)
        )

        root.addView(
            gameTitle,
            marginParams(50)
        )

        val gameVersion = TextView(this)

        gameVersion.text =
            "Minecraft Java Edition\n1.16.5"

        gameVersion.textSize = 18f
        gameVersion.setTextColor(Color.WHITE)
        gameVersion.gravity = Gravity.CENTER
        gameVersion.background = rounded(
            Color.rgb(18, 7, 13),
            16
        )

        root.addView(
            gameVersion,
            marginParams(85)
        )

        // Кнопка
        val saveButton = Button(this)

        saveButton.text = "СОХРАНИТЬ"
        saveButton.textSize = 15f
        saveButton.setTextColor(Color.WHITE)

        saveButton.background = rounded(
            Color.rgb(170, 15, 42),
            16
        )

        saveButton.setOnClickListener {

            prefs.edit()
                .putString(
                    "nickname",
                    nickname.text.toString().trim()
                )
                .apply()

            finish()
        }

        root.addView(
            saveButton,
            marginParams(65)
        )

        val versionText = TextView(this)

        versionText.text =
            "BLOODMOON LAUNCHER • v0.1"

        versionText.textSize = 10f
        versionText.setTextColor(
            Color.rgb(80, 65, 70)
        )
        versionText.gravity = Gravity.CENTER

        root.addView(
            versionText,
            marginParams(40)
        )

        setContentView(scrollView)
    }

    private fun marginParams(
        height: Int
    ): LinearLayout.LayoutParams {

        val params = LinearLayout.LayoutParams(
            -1,
            height
        )

        params.topMargin = 14

        return params
    }

    private fun rounded(
        color: Int,
        radius: Int
    ): GradientDrawable {

        val drawable = GradientDrawable()

        drawable.setColor(color)
        drawable.cornerRadius = radius.toFloat()

        return drawable
    }
}

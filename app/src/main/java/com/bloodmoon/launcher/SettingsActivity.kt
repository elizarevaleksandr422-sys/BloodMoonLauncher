package com.bloodmoon.launcher

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private val prefsName = "bloodmoon_settings"

    private val red = Color.rgb(215, 25, 53)
    private val darkRed = Color.rgb(80, 8, 22)
    private val background = Color.rgb(5, 3, 8)
    private val card = Color.rgb(18, 7, 13)
    private val white = Color.rgb(240, 235, 238)
    private val gray = Color.rgb(160, 150, 155)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.setBackgroundColor(background)
        root.setPadding(24, 35, 24, 24)

        // Заголовок
        val title = TextView(this)

        title.text = "BLOODMOON"
        title.textSize = 30f
        title.setTextColor(red)
        title.setGravity(Gravity.CENTER)

        root.addView(
            title,
            LinearLayout.LayoutParams(
                -1,
                55
            )
        )

        // Подзаголовок
        val subtitle = TextView(this)

        subtitle.text = "НАСТРОЙКИ ЛАУНЧЕРА"
        subtitle.textSize = 12f
        subtitle.setTextColor(gray)
        subtitle.setGravity(Gravity.CENTER)

        root.addView(
            subtitle,
            LinearLayout.LayoutParams(
                -1,
                35
            )
        )

        // Карточка профиля
        val profileCard = createCard()

        val profileTitle = createLabel("ПРОФИЛЬ")

        profileCard.addView(profileTitle)

        val nickname = EditText(this)

        nickname.hint = "Введите никнейм"
        nickname.textSize = 17f
        nickname.setTextColor(white)
        nickname.setHintTextColor(Color.rgb(90, 80, 85))
        nickname.setSingleLine(true)
        nickname.setPadding(18, 0, 18, 0)

        nickname.setText(
            prefs.getString("nickname", "")
        )

        nickname.background = roundedBackground(
            Color.rgb(25, 10, 17),
            14
        )

        val nicknameParams = LinearLayout.LayoutParams(
            -1,
            58
        )

        nicknameParams.topMargin = 12

        profileCard.addView(
            nickname,
            nicknameParams
        )

        root.addView(
            profileCard,
            cardParams()
        )

        // Карточка ОЗУ
        val ramCard = createCard()

        val ramTitle = createLabel("ОПЕРАТИВНАЯ ПАМЯТЬ")

        ramCard.addView(ramTitle)

        val currentRam = prefs.getInt("ram", 2)

        val ramValue = TextView(this)

        ramValue.text = "$currentRam ГБ"
        ramValue.textSize = 20f
        ramValue.setTextColor(red)
        ramValue.gravity = Gravity.CENTER

        val ramValueParams = LinearLayout.LayoutParams(
            -1,
            45
        )

        ramValueParams.topMargin = 8

        ramCard.addView(
            ramValue,
            ramValueParams
        )

        val ramSeek = SeekBar(this)

        ramSeek.max = 7
        ramSeek.progress = currentRam - 1

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
                ) {}

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {}
            }
        )

        ramCard.addView(ramSeek)

        val ramInfo = TextView(this)

        ramInfo.text = "Рекомендуется: 2–4 ГБ"
        ramInfo.textSize = 11f
        ramInfo.setTextColor(gray)
        ramInfo.gravity = Gravity.CENTER

        ramCard.addView(ramInfo)

        root.addView(
            ramCard,
            cardParams()
        )

        // Версия
        val versionCard = createCard()

        val versionTitle = createLabel("ВЕРСИЯ ИГРЫ")

        versionCard.addView(versionTitle)

        val version = TextView(this)

        version.text = "Minecraft Java Edition\n1.16.5"
        version.textSize = 17f
        version.setTextColor(white)
        version.setGravity(Gravity.CENTER)
        version.setPadding(0, 12, 0, 12)

        versionCard.addView(version)

        root.addView(
            versionCard,
            cardParams()
        )

        // Кнопка сохранения
        val saveButton = Button(this)

        saveButton.text = "СОХРАНИТЬ"
        saveButton.textSize = 15f
        saveButton.setTextColor(Color.WHITE)
        saveButton.background = roundedBackground(
            red,
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

        val saveParams = LinearLayout.LayoutParams(
            -1,
            60
        )

        saveParams.topMargin = 18

        root.addView(
            saveButton,
            saveParams
        )

        // Версия лаунчера
        val launcherVersion = TextView(this)

        launcherVersion.text = "BLOODMOON LAUNCHER • v0.1"
        launcherVersion.textSize = 10f
        launcherVersion.setTextColor(
            Color.rgb(80, 65, 70)
        )
        launcherVersion.gravity = Gravity.CENTER

        val launcherVersionParams = LinearLayout.LayoutParams(
            -1,
            35
        )

        launcherVersionParams.topMargin = 8

        root.addView(
            launcherVersion,
            launcherVersionParams
        )

        setContentView(root)
    }

    private fun createCard(): LinearLayout {

        val layout = LinearLayout(this)

        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(18, 16, 18, 16)

        layout.background = roundedBackground(
            card,
            18
        )

        return layout
    }

    private fun createLabel(text: String): TextView {

        val label = TextView(this)

        label.text = text
        label.textSize = 12f
        label.setTextColor(gray)

        return label
    }

    private fun cardParams(): LinearLayout.LayoutParams {

        val params = LinearLayout.LayoutParams(
            -1,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.topMargin = 14

        return params
    }

    private fun roundedBackground(
        color: Int,
        radius: Int
    ): GradientDrawable {

        val drawable = GradientDrawable()

        drawable.setColor(color)
        drawable.cornerRadius = radius.toFloat()

        return drawable
    }
}

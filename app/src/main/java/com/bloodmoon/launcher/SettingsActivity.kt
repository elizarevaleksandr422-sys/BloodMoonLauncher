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

    private val red = Color.rgb(215, 25, 53)
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

        // Основной ScrollView
        val scroll = ScrollView(this)

        scroll.setBackgroundColor(background)

        // Контейнер
        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.setPadding(22, 28, 22, 28)

        scroll.addView(
            root,
            ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.WRAP_CONTENT
            )
        )

        // Заголовок
        val title = TextView(this)

        title.text = "BLOODMOON"
        title.textSize = 30f
        title.setTextColor(red)
        title.gravity = Gravity.CENTER

        root.addView(
            title,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                60
            )
        )

        // Подзаголовок
        val subtitle = TextView(this)

        subtitle.text = "НАСТРОЙКИ ЛАУНЧЕРА"
        subtitle.textSize = 12f
        subtitle.setTextColor(gray)
        subtitle.gravity = Gravity.CENTER

        root.addView(
            subtitle,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                35
            )
        )

        // =========================
        // ПРОФИЛЬ
        // =========================

        val profileCard = createCard()

        profileCard.addView(
            createLabel("ПРОФИЛЬ")
        )

        val nickname = EditText(this)

        nickname.hint = "Введите никнейм"
        nickname.textSize = 17f
        nickname.setTextColor(white)
        nickname.setHintTextColor(
            Color.rgb(100, 90, 95)
        )
        nickname.setSingleLine(true)

        nickname.setText(
            prefs.getString("nickname", "")
        )

        nickname.background = roundedBackground(
            Color.rgb(25, 10, 17),
            14
        )

        nickname.setPadding(
            18,
            0,
            18,
            0
        )

        val nicknameParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            60
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

        // =========================
        // ОЗУ
        // =========================

        val ramCard = createCard()

        ramCard.addView(
            createLabel("ОПЕРАТИВНАЯ ПАМЯТЬ")
        )

        val currentRam = prefs.getInt(
            "ram",
            2
        ).coerceIn(1, 8)

        val ramValue = TextView(this)

        ramValue.text = "$currentRam ГБ"
        ramValue.textSize = 22f
        ramValue.setTextColor(red)
        ramValue.gravity = Gravity.CENTER

        ramCard.addView(
            ramValue,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                50
            )
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
                ) {
                }

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {
                }
            }
        )

        ramCard.addView(
            ramSeek,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                55
            )
        )

        val ramInfo = TextView(this)

        ramInfo.text = "Доступно: 1–8 ГБ"
        ramInfo.textSize = 11f
        ramInfo.setTextColor(gray)
        ramInfo.gravity = Gravity.CENTER

        ramCard.addView(
            ramInfo,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                30
            )
        )

        root.addView(
            ramCard,
            cardParams()
        )

        // =========================
        // MINECRAFT
        // =========================

        val versionCard = createCard()

        versionCard.addView(
            createLabel("ВЕРСИЯ ИГРЫ")
        )

        val version = TextView(this)

        version.text = "Minecraft Java Edition\n1.16.5"
        version.textSize = 18f
        version.setTextColor(white)
        version.gravity = Gravity.CENTER
        version.setPadding(0, 14, 0, 14)

        versionCard.addView(
            version,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                75
            )
        )

        root.addView(
            versionCard,
            cardParams()
        )

        // =========================
        // СОХРАНИТЬ
        // =========================

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
            LinearLayout.LayoutParams.MATCH_PARENT,
            62
        )

        saveParams.topMargin = 20

        root.addView(
            saveButton,
            saveParams
        )

        // Версия лаунчера
        val launcherVersion = TextView(this)

        launcherVersion.text =
            "BLOODMOON LAUNCHER • v0.1"

        launcherVersion.textSize = 10f
        launcherVersion.setTextColor(
            Color.rgb(80, 65, 70)
        )
        launcherVersion.gravity = Gravity.CENTER

        val versionParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            40
        )

        versionParams.topMargin = 8

        root.addView(
            launcherVersion,
            versionParams
        )

        setContentView(scroll)
    }

    private fun createCard(): LinearLayout {

        val cardLayout = LinearLayout(this)

        cardLayout.orientation =
            LinearLayout.VERTICAL

        cardLayout.setPadding(
            18,
            18,
            18,
            18
        )

        cardLayout.background =
            roundedBackground(
                card,
                18
            )

        return cardLayout
    }

    private fun createLabel(
        text: String
    ): TextView {

        val label = TextView(this)

        label.text = text
        label.textSize = 12f
        label.setTextColor(gray)

        return label
    }

    private fun cardParams():
            LinearLayout.LayoutParams {

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
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
        drawable.cornerRadius =
            radius.toFloat()

        return drawable
    }
}

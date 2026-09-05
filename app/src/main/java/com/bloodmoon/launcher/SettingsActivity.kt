package com.bloodmoon.launcher

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private val prefsName = "bloodmoon_settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = android.widget.LinearLayout(this)

        layout.orientation = android.widget.LinearLayout.VERTICAL
        layout.setPadding(35, 50, 35, 35)
        layout.setBackgroundColor(android.graphics.Color.rgb(5, 3, 8))

        val title = TextView(this)

        title.text = "BLOODMOON SETTINGS"
        title.textSize = 28f
        title.setTextColor(android.graphics.Color.rgb(220, 25, 55))
        title.setPadding(0, 0, 0, 35)

        layout.addView(title)

        // Никнейм
        val nicknameTitle = TextView(this)

        nicknameTitle.text = "НИКНЕЙМ"
        nicknameTitle.textSize = 14f
        nicknameTitle.setTextColor(android.graphics.Color.LTGRAY)

        layout.addView(nicknameTitle)

        val nickname = EditText(this)

        nickname.hint = "Введите ник"
        nickname.setTextColor(android.graphics.Color.WHITE)
        nickname.setHintTextColor(android.graphics.Color.GRAY)
        nickname.textSize = 17f

        val prefs = getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )

        nickname.setText(
            prefs.getString("nickname", "")
        )

        layout.addView(nickname)

        // ОЗУ
        val ramTitle = TextView(this)

        ramTitle.text = "ОЗУ: 2 ГБ"
        ramTitle.textSize = 14f
        ramTitle.setTextColor(android.graphics.Color.LTGRAY)
        ramTitle.setPadding(0, 35, 0, 10)

        layout.addView(ramTitle)

        val ramBar = SeekBar(this)

        ramBar.max = 3

        val savedRam = prefs.getInt("ram", 2)

        ramBar.progress = savedRam - 1

        ramTitle.text = "ОЗУ: ${savedRam} ГБ"

        ramBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                    val ram = progress + 1

                    ramTitle.text = "ОЗУ: $ram ГБ"

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

        layout.addView(ramBar)

        // Версия
        val version = TextView(this)

        version.text = "Версия Minecraft\n1.16.5"
        version.textSize = 17f
        version.setTextColor(android.graphics.Color.WHITE)
        version.setPadding(0, 35, 0, 35)

        layout.addView(version)

        // Сохранить
        val saveButton = Button(this)

        saveButton.text = "СОХРАНИТЬ"
        saveButton.setTextColor(android.graphics.Color.WHITE)
        saveButton.setBackgroundColor(
            android.graphics.Color.rgb(150, 15, 40)
        )

        saveButton.setOnClickListener {

            prefs.edit()
                .putString(
                    "nickname",
                    nickname.text.toString()
                )
                .apply()

            finish()
        }

        layout.addView(saveButton)

        setContentView(layout)
    }
}

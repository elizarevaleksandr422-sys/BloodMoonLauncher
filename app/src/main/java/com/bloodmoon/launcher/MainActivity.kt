package com.bloodmoon.launcher

import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER
        root.setPadding(40, 40, 40, 40)
        root.setBackgroundColor(Color.rgb(8, 5, 10))

        val title = TextView(this)

        title.text = "BLOODMOON"
        title.textSize = 36f
        title.setTextColor(Color.rgb(190, 20, 35))
        title.typeface = Typeface.DEFAULT_BOLD
        title.gravity = Gravity.CENTER

        val subtitle = TextView(this)

        subtitle.text = "MINECRAFT 1.16.5"
        subtitle.textSize = 14f
        subtitle.setTextColor(Color.LTGRAY)
        subtitle.gravity = Gravity.CENTER

        val playButton = Button(this)

        playButton.text = "ИГРАТЬ"
        playButton.textSize = 18f
        playButton.setTextColor(Color.WHITE)
        playButton.setBackgroundColor(Color.rgb(120, 10, 25))

        val buttonParams = LinearLayout.LayoutParams(
            500,
            150
        )

        buttonParams.setMargins(0, 80, 0, 0)

        root.addView(title)
        root.addView(subtitle)
        root.addView(playButton, buttonParams)

        setContentView(root)
    }
}

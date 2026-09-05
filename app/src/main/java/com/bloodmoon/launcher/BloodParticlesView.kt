package com.bloodmoon.launcher

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.view.View
import kotlin.math.sin
import kotlin.random.Random

class BloodParticlesView(context: Context) : View(context) {

    private data class Particle(
        var x: Float,
        var y: Float,
        var size: Float,
        var speed: Float,
        var alpha: Int,
        var phase: Float
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val particles = mutableListOf<Particle>()

    private val fogPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var time = 0f

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        repeat(45) {
            particles.add(
                Particle(
                    x = Random.nextFloat(),
                    y = Random.nextFloat(),
                    size = Random.nextFloat() * 5f + 1f,
                    speed = Random.nextFloat() * 0.0008f + 0.0003f,
                    alpha = Random.nextInt(50, 170),
                    phase = Random.nextFloat() * 6.28f
                )
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        time += 1f

        val width = width.toFloat()
        val height = height.toFloat()

        // Кровавый туман
        val fogGradient = RadialGradient(
            width * 0.5f,
            height * 0.35f,
            width * 0.65f,
            intArrayOf(
                0x18000000,
                0x100F0010,
                0x08000000,
                0x00000000
            ),
            null,
            Shader.TileMode.CLAMP
        )

        fogPaint.shader = fogGradient
        canvas.drawRect(0f, 0f, width, height, fogPaint)

        // Частицы
        particles.forEach { particle ->

            particle.y -= particle.speed

            if (particle.y < -0.05f) {
                particle.y = 1.05f
                particle.x = Random.nextFloat()
            }

            val wave =
                sin(time * 0.01f + particle.phase) * 18f

            val px = particle.x * width + wave
            val py = particle.y * height

            paint.color =
                (particle.alpha shl 24) or 0x00D91B36

            paint.setShadowLayer(
                particle.size * 3f,
                0f,
                0f,
                0x99FF001F
            )

            canvas.drawCircle(
                px,
                py,
                particle.size,
                paint
            )
        }

        // Тёмный нижний туман
        val bottomFog = RadialGradient(
            width * 0.5f,
            height,
            width * 0.8f,
            intArrayOf(
                0x30000000,
                0x180F0008,
                0x00000000
            ),
            null,
            Shader.TileMode.CLAMP
        )

        fogPaint.shader = bottomFog

        canvas.drawRect(
            0f,
            height * 0.55f,
            width,
            height,
            fogPaint
        )

        postInvalidateOnAnimation()
    }
}

package com.bloodmoon.launcher

import android.content.Context

object RendererManager {

    private const val PREFS = "bloodmoon_settings"
    private const val KEY_RENDERER = "renderer"

    const val HOLY_GL4ES = "Holy GL4ES"
    const val GL4ES = "GL4ES"
    const val ZINK = "Zink"
    const val LTW = "LTW"

    fun getRenderer(context: Context): String {

        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        return prefs.getString(
            KEY_RENDERER,
            HOLY_GL4ES
        ) ?: HOLY_GL4ES
    }

    fun setRenderer(
        context: Context,
        renderer: String
    ) {

        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(
                KEY_RENDERER,
                renderer
            )
            .apply()
    }

    fun getRendererId(
        context: Context
    ): String {

        return when (getRenderer(context)) {

            HOLY_GL4ES ->
                "holy_gl4es"

            GL4ES ->
                "gl4es"

            ZINK ->
                "zink"

            LTW ->
                "ltw"

            else ->
                "holy_gl4es"
        }
    }
}

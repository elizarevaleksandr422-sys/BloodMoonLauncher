package com.bloodmoon.launcher

import android.content.Context
import java.io.File

object MinecraftManager {

    private const val GAME_FOLDER = "minecraft"

    fun getGameDirectory(context: Context): File {

        val directory = File(
            context.filesDir,
            GAME_FOLDER
        )

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    fun getRuntimeDirectory(context: Context): File {

        val directory = File(
            getGameDirectory(context),
            "runtime"
        )

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    fun getLibrariesDirectory(context: Context): File {

        val directory = File(
            getGameDirectory(context),
            "libraries"
        )

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    fun getVersionsDirectory(context: Context): File {

        val directory = File(
            getGameDirectory(context),
            "versions"
        )

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    fun getAssetsDirectory(context: Context): File {

        val directory = File(
            getGameDirectory(context),
            "assets"
        )

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    fun isMinecraftInstalled(
        context: Context
    ): Boolean {

        val versionDirectory = File(
            getVersionsDirectory(context),
            "1.16.5"
        )

        val minecraftJar = File(
            versionDirectory,
            "1.16.5.jar"
        )

        return minecraftJar.exists()
    }

    fun getMinecraftJar(
        context: Context
    ): File {

        return File(
            getVersionsDirectory(context),
            "1.16.5/1.16.5.jar"
        )
    }

    fun isRuntimeInstalled(
        context: Context
    ): Boolean {

        val runtime = getRuntimeDirectory(context)

        return runtime.exists() &&
                runtime.listFiles()?.isNotEmpty() == true
    }
}

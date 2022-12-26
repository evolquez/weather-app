package com.example.weatherapp.util

import android.content.Intent
import android.os.Build
import java.io.Serializable

object Util {
    const val IMAGE_URL_FORMAT = "https://openweathermap.org/img/wn/%s@2x.png"

    @Suppress("DEPRECATION", "UNCHECKED_CAST")
    fun <T : Serializable?> Intent.getSerializable(key: String, clazz: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializableExtra(key, clazz)!! else (getSerializableExtra(key) as T)
    }
}
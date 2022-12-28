package com.example.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainDto(
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
): Serializable
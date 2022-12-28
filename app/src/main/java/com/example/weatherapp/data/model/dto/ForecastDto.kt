package com.example.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ForecastDto(
    val id: Int,
    val name: String,
    val coord: CoordinateDto,
    val sys: SysDto,
    val weather: List<WeatherConditionDto>,
    val main: MainDto,
    val dt: Long,
    val pop: Double,
    @SerializedName("dt_txt")
    val dtTxt: String?
): Serializable

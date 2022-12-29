package com.example.weatherapp.data.model.dto

import java.io.Serializable

data class CityWeatherInfoDto (
    val cityId: Int,
    val cityName: String,
    val country: String,
    val cityLat: Double,
    val cityLon: Double
): Serializable
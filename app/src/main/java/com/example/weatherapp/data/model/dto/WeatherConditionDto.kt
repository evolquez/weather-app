package com.example.weatherapp.data.model.dto

import java.io.Serializable

data class WeatherConditionDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
): Serializable
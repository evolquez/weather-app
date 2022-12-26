package com.example.weatherapp.data.model.dto

data class WeatherConditionDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
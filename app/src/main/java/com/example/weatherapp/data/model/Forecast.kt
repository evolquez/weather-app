package com.example.weatherapp.data.model

data class Forecast(
    val city: City,
    val currentTemp: Double,
    val highestTemp: Double,
    val lowerTemp: Double,
    val precipitationPercent: Double,
    val icon: String,
    val timestampMillis: Long
)

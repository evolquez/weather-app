package com.example.weatherapp.data.model.dto

data class CityWeatherDto(
    val id: Int,
    val name: String,
    val coord: CoordinateDto,
    val sys: SysDto,
    val weather: List<WeatherConditionDto>,
    val main: MainDto,
    val dt: Long
)

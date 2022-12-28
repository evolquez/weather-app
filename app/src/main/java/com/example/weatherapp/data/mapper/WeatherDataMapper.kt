package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.dto.ForecastDto
import com.example.weatherapp.data.model.entity.ForecastInfo
import com.example.weatherapp.data.model.entity.Weather

object WeatherDataMapper {

    fun toEntity(weatherDto: ForecastDto) = Weather(
            weatherDto.id,
            weatherDto.name,
            weatherDto.sys.country,
            weatherDto.sys.timezone,
            weatherDto.coord.lat,
            weatherDto.coord.lon,
            ForecastInfo(
                weatherDto.main.temp,
                weatherDto.main.tempMax,
                weatherDto.main.tempMin,
                weatherDto.weather[0].icon,
                weatherDto.dtTxt, 0.0
            ),
            weatherDto.dt
    )
}
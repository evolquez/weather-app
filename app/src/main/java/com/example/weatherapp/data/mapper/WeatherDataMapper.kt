package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.dto.ForecastDto
import com.example.weatherapp.data.model.entity.DbEntity
import com.example.weatherapp.data.model.entity.Forecast
import com.example.weatherapp.data.model.entity.ForecastInfo
import com.example.weatherapp.data.model.entity.Weather

object WeatherDataMapper {

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    fun <T: DbEntity> toEntity(dto: ForecastDto, clazz: Class<T>, weatherId: Int? = 0): T {
        return when(clazz) {
            Weather::class.java -> {
                Weather(
                    dto.id,
                    dto.name,
                    dto.sys.country,
                    dto.sys.timezone,
                    dto.coord.lat,
                    dto.coord.lon,
                    ForecastInfo(
                        dto.main.temp,
                        dto.main.tempMax,
                        dto.main.tempMin,
                        dto.weather[0].icon,
                        dto.dtTxt,
                        0.0
                    ),
                    dto.dt
                )
            }
            Forecast::class.java -> {
                Forecast(
                    weatherId = weatherId ?: 0,
                    forecastInfo = ForecastInfo(
                        dto.main.temp,
                        dto.main.tempMax,
                        dto.main.tempMin,
                        dto.weather[0].icon,
                        dto.dtTxt,
                        dto.pop
                    )
                )
            }
            else -> {}
        } as T
    }
}
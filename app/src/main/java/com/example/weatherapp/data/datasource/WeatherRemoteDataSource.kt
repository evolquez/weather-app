package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.model.dto.ForecastResultDto
import com.example.weatherapp.data.network.WeatherService
import retrofit2.Response
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val weatherService: WeatherService){

    suspend fun fetchWeather(): Response<ForecastResultDto> {
        return weatherService.fetchWeatherByCities(ids = CITY_IDS,  appId = APP_ID)
    }

    suspend fun fetchFiveDayWeatherForecast(latitude: Double, longitude: Double): Response<ForecastResultDto> {
        return weatherService.getFiveDayWeatherForecast(
            appId = APP_ID,
            lat = latitude,
            lon = longitude
        )
    }

    companion object {
        private const val APP_ID = "cd2952292cf985c7f0d20a4d5edafb86"
        // 20 cities
        private const val CITY_IDS = "3492908,3492914,3494242,3493175,5125771,5128581,5134086,5145215,4164138,5164466,3435910,4568127,3674962,3688689,3117735,3128760,3451190,6077243,6325494,2988507"
    }
}
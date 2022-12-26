package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.model.dto.CityWeatherResultDto
import com.example.weatherapp.data.network.WeatherService
import retrofit2.Response
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val weatherService: WeatherService){
    // 20 cities
    private val cityIds = "3492908,3492914,3494242,3493175,5125771,5128581,5134086,5145215,4164138,5164466,3435910,4568127,3674962,3688689,3117735,3128760,3451190,6077243,6325494,2988507"
    private val appId = "cd2952292cf985c7f0d20a4d5edafb86"

    suspend fun fetchCityWeather(): Response<CityWeatherResultDto> {

        return weatherService.getCityWeather(ids = cityIds,  appId = appId)
    }
}
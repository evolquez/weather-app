package com.example.weatherapp.data.repository

import com.example.weatherapp.data.datasource.WeatherRemoteDataSource
import com.example.weatherapp.data.model.dto.CoordinateDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val remoteDataSource: WeatherRemoteDataSource) {

    suspend fun fetchCityWeatherData() = remoteDataSource.fetchCityWeather()

    suspend fun fetchFiveDayWeatherForecast(coordinate: CoordinateDto) = remoteDataSource.fetchFiveDayWeatherForecast(coordinate)
}
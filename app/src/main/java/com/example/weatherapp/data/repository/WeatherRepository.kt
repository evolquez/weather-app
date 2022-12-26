package com.example.weatherapp.data.repository

import com.example.weatherapp.data.datasource.WeatherRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val remoteDataSource: WeatherRemoteDataSource) {

    suspend fun fetchCityWeatherData() = remoteDataSource.fetchCityWeather()
}
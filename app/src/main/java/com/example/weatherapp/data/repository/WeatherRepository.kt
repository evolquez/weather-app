package com.example.weatherapp.data.repository

import com.example.weatherapp.data.datasource.WeatherRemoteDataSource

class WeatherRepository (private val weatherRemoteDataSource: WeatherRemoteDataSource) {

    suspend fun fetchCityWeatherData() = weatherRemoteDataSource.fetchCityWeather()
}
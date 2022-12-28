package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.model.dao.WeatherDao
import com.example.weatherapp.data.model.entity.Weather
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(private val weatherDao: WeatherDao) {

    suspend fun fetchWeatherByCities(): List<Weather> = weatherDao.fetchWeatherByCities()

    suspend fun updateWeather(weatherList: List<Weather>) = weatherDao.updateWeather(weatherList)
}
package com.example.weatherapp.data.repository

import com.example.weatherapp.data.datasource.WeatherLocalDataSource
import com.example.weatherapp.data.datasource.WeatherRemoteDataSource
import com.example.weatherapp.data.mapper.WeatherDataMapper
import com.example.weatherapp.data.model.entity.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) {

    suspend fun fetchWeather(): List<Weather> {
        val localWeather = this.localDataSource.fetchWeatherByCities()

        if(localWeather.isEmpty()){
            val remoteWeather = this.remoteDataSource.fetchWeather()
            val weatherList: MutableList<Weather> = mutableListOf()

            remoteWeather.body()?.let { it ->
                it.list.forEach { dto -> weatherList.add(WeatherDataMapper.toEntity(dto)) }
            }

            this.localDataSource.updateWeather(weatherList)
        }
        return localDataSource.fetchWeatherByCities()
    }

    suspend fun fetchFiveDayWeatherForecast(lat: Double, lon: Double) = remoteDataSource.fetchFiveDayWeatherForecast(lat, lon)
}
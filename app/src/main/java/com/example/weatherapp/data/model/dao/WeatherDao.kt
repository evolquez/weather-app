package com.example.weatherapp.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.data.model.entity.Forecast
import com.example.weatherapp.data.model.entity.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun fetchWeatherByCities(): List<Weather>

    @Query("SELECT * FROM forecast WHERE weather_id = :weatherId")
    suspend fun fetchForecastByCity(weatherId: Int): List<Forecast>

    @Insert
    suspend fun updateWeather(weather: List<Weather>)

    @Insert
    suspend fun updateForecast(forecast: List<Forecast>)


    @Query("DELETE FROM weather")
    fun deleteAllWeather()

    @Query("DELETE FROM forecast WHERE weather_id = :weatherId")
    fun deleteAllForecast(weatherId: Int)
}
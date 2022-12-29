package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.datasource.WeatherLocalDataSource
import com.example.weatherapp.data.datasource.WeatherRemoteDataSource
import com.example.weatherapp.data.mapper.WeatherDataMapper
import com.example.weatherapp.data.model.entity.Forecast
import com.example.weatherapp.data.model.entity.Weather
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) {

    suspend fun fetchWeather(): List<Weather> {

        try {
            val remoteWeather = this.remoteDataSource.fetchWeather()
            val weatherList: MutableList<Weather> = mutableListOf()

            remoteWeather.body()?.let { it ->
                it.list.forEach { dto -> weatherList.add(WeatherDataMapper.toEntity(dto, Weather::class.java)) }
            }
            this.localDataSource.deleteAllWeather()
            this.localDataSource.updateWeather(weatherList)

        }catch(e: UnknownHostException) {
            Log.d(TAG, "Could not fetch weather from remote")
            Log.d(TAG, e.message.toString())
        }

        return localDataSource.fetchWeatherByCities()
    }

    suspend fun fetchFiveDayWeatherForecast(weatherId: Int, lat: Double, lon: Double): List<Forecast> {

        try{
            val remoteForecast = remoteDataSource.fetchFiveDayWeatherForecast(lat, lon)
            val forecastList: MutableList<Forecast> = mutableListOf()

            remoteForecast.body()?.let {
                it.list.forEach{ dto -> forecastList.add(WeatherDataMapper.toEntity(dto, Forecast::class.java, weatherId))}
            }
            this.localDataSource.deleteAllForecast(weatherId)
            this.localDataSource.updateForeCastByWeather(clearFiveDayForecastList(forecastList))
        }catch (e: UnknownHostException) {
            Log.d(TAG, "Could not fetch forecast from remote")
            Log.d(TAG, e.message.toString())
        }

        return localDataSource.fetchForecastByWeather(weatherId)
    }

    /**
     * Forecast API return 5-day forecast with 3-hour step for each day, so this makes the list longer.
     * This fun makes sure to clear the list and only return single 5-day forecast.
     * @param list
     * @return ArrayList
     * */
    private fun clearFiveDayForecastList(list: List<Forecast>): List<Forecast> {
        val forecastMap = mutableMapOf<String, Forecast>()

        list.forEach{ forecast ->
            val date = forecast.forecastInfo.dateText?.split(" ")?.get(0) ?: ""
            if(!forecastMap.containsKey(date)){
                forecastMap[date] = forecast
            }
        }

        return forecastMap.values.toList()
    }

    companion object {
        private const val TAG = "WeatherRepository"
    }
}
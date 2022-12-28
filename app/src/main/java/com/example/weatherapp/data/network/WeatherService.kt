package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.dto.ForecastResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("group?units=metric")
    suspend fun fetchWeatherByCities( @Query("id") ids: String,
                                @Query("appid") appId: String): Response<ForecastResultDto>

    @GET("forecast?units=metric")
    suspend fun getFiveDayWeatherForecast( @Query("appid") appId: String,
                                           @Query("lat") lat: Double,
                                           @Query("lon") lon: Double): Response<ForecastResultDto>
}
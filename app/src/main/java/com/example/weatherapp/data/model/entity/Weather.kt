package com.example.weatherapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "country_code") val countryCode: String,
    @ColumnInfo(name = "country_timezone") val countryTimeZone: Int,
    @ColumnInfo(name = "city_lat") val cityLat: Double,
    @ColumnInfo(name = "city_lon") val cityLon: Double,
    @Embedded val forecastInfo: ForecastInfo,
    val date: Long
): DbEntity(),  java.io.Serializable

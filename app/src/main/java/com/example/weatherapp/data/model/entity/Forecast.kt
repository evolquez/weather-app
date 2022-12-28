package com.example.weatherapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "weather_id") val weatherId: Int,
    @Embedded val forecastInfo: ForecastInfo,
)

data class ForecastInfo(
    @ColumnInfo(name ="current_temp") val currentTemp: Double,
    @ColumnInfo(name ="max_temp") val maxTemp: Double,
    @ColumnInfo(name ="min_temp") val minTemp: Double,
    @ColumnInfo(name = "weather_icon") val weatherIcon: String,
    @ColumnInfo(name = "date_text") val dateText: String?,
    val precipitation: Double
): java.io.Serializable

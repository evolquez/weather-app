package com.example.weatherapp.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.model.dao.WeatherDao
import com.example.weatherapp.data.model.entity.Forecast
import com.example.weatherapp.data.model.entity.Weather

@Database(entities = [Weather::class, Forecast::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
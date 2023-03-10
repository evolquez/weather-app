package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.model.dao.WeatherDao
import com.example.weatherapp.data.model.database.WeatherDatabase
import com.example.weatherapp.data.network.WeatherService
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun weatherService(): WeatherService {
        return Retrofit
                .Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun weatherDao(applicationContext: Context): WeatherDao {
        return Room
            .databaseBuilder(
                applicationContext,
                WeatherDatabase::class.java,
                "weather-database"
            ).build().weatherDao()
    }

    @Singleton
    @Provides
    fun picasso(): Picasso = Picasso.get()
}
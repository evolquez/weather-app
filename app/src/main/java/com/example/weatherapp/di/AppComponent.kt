package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.ui.forecast.forecast.di.FiveDayForecastComponent
import com.example.weatherapp.ui.forecast.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    SubcomponentsModule::class,
    ViewModelBuilderModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun fiveDayForecastComponent(): FiveDayForecastComponent.Factory
}
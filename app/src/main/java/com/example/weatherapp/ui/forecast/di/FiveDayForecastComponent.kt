package com.example.weatherapp.ui.forecast.di

import com.example.weatherapp.di.ActivityScope
import com.example.weatherapp.ui.forecast.FiveDayForecastActivity
import com.example.weatherapp.ui.forecast.FiveDayForecastAdapter
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [FiveDayForecastModule::class])
interface FiveDayForecastComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FiveDayForecastComponent
    }

    fun inject(activity: FiveDayForecastActivity)
    fun inject(adapter: FiveDayForecastAdapter)
}
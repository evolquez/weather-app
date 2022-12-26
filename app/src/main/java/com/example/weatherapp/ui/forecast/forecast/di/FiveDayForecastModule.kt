package com.example.weatherapp.ui.forecast.forecast.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.di.ViewModelKey
import com.example.weatherapp.ui.forecast.forecast.FiveDayForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FiveDayForecastModule {
    @Binds
    @IntoMap
    @ViewModelKey(FiveDayForecastViewModel::class)
    abstract fun bindViewModel(viewModel: FiveDayForecastViewModel): ViewModel
}
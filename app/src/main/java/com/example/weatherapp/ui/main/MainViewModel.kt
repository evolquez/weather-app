package com.example.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.entity.Weather
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.di.ActivityScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val weatherList = MutableLiveData<List<Weather>>()

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherList.postValue(repository.fetchWeather())
        }
    }

}
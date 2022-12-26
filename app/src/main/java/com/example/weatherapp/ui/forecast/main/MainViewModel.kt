package com.example.weatherapp.ui.forecast.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.dto.CityWeatherDto
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.di.ActivityScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val weatherList = MutableLiveData<List<CityWeatherDto>>()

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result  = repository.fetchCityWeatherData()
            result.body()?.let {
                weatherList.postValue(it.list)
            }
        }
    }

}
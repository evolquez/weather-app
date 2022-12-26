package com.example.weatherapp.ui.forecast.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.datasource.WeatherRemoteDataSource
import com.example.weatherapp.data.model.dto.CityWeatherDto
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository: WeatherRepository = WeatherRepository(WeatherRemoteDataSource())

    val weatherList = MutableLiveData<List<CityWeatherDto>>()

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result  = repository.fetchCityWeatherData()
            weatherList.postValue(result.body()?.list)
        }
    }

}
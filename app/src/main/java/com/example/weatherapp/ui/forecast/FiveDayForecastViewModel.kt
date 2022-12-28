package com.example.weatherapp.ui.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.entity.Forecast
import com.example.weatherapp.data.model.entity.Weather
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiveDayForecastViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val weather: MutableLiveData<Weather> by lazy {
        MutableLiveData<Weather>()
    }

    val forecastList = MutableLiveData<List<Forecast>>()


    fun getFiveDayWeatherForecast(weatherId: Int, lat: Double, lon: Double) {
        viewModelScope.launch {
            forecastList.postValue(repository.fetchFiveDayWeatherForecast(weatherId, lat, lon))
        }
    }
}
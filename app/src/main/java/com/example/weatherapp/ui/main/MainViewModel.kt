package com.example.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.di.ActivityScope
import com.example.weatherapp.ui.common.holder.item.BaseItem
import com.example.weatherapp.ui.common.holder.item.EmptyItem
import com.example.weatherapp.ui.common.holder.item.LoaderItem
import com.example.weatherapp.ui.common.holder.item.WeatherItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val weatherList = MutableLiveData<List<BaseItem>>()

    private val _weatherListItem = mutableListOf<BaseItem>()

    fun getWeatherData() {
        _weatherListItem.add(LoaderItem())

        weatherList.postValue(_weatherListItem)

        viewModelScope.launch(Dispatchers.IO) {
            val listWeather = repository.fetchWeather()
            _weatherListItem.clear()

            if(listWeather.isEmpty()){
                _weatherListItem.add(EmptyItem())
            }else{
                listWeather.forEach {w ->
                    _weatherListItem.add(
                        WeatherItem(
                            cityId = w.id,
                            cityLat = w.cityLat,
                            cityLon = w.cityLon,
                            city = w.cityName,
                            countryCode = w.countryCode,
                            countryTimeZone = w.countryTimeZone,
                            currentTemp = w.forecastInfo.currentTemp,
                            maxTemp = w.forecastInfo.maxTemp,
                            minTemp = w.forecastInfo.minTemp,
                            dateInMillis = w.date,
                            icon = w.forecastInfo.weatherIcon,
                            precipitation = 0.0
                        )
                    )
                }
            }
            weatherList.postValue(_weatherListItem)
        }
    }
}
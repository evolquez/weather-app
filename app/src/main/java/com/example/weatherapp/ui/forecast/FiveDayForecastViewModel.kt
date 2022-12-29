package com.example.weatherapp.ui.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.dto.CityWeatherInfoDto
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.ui.common.holder.item.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiveDayForecastViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val weatherInfo: MutableLiveData<CityWeatherInfoDto> by lazy {
        MutableLiveData<CityWeatherInfoDto>()
    }

    val forecastList = MutableLiveData<List<BaseItem>>()

    private val _forecastListItem: MutableList<BaseItem> = mutableListOf()

    fun getFiveDayWeatherForecast(weatherId: Int, lat: Double, lon: Double) {
        _forecastListItem.add(LoaderItem())

        forecastList.postValue(_forecastListItem)

        viewModelScope.launch {
            val forecastListResult = repository.fetchFiveDayWeatherForecast(weatherId, lat, lon)

            _forecastListItem.clear()

            if(forecastListResult.isNotEmpty()) {
                forecastListResult.forEach { forecast ->
                    _forecastListItem.add(
                        ForecastItem(
                            date = (forecast.forecastInfo.dateText?.split(" ")?.get(0) ?: ""),
                            maxTemp = forecast.forecastInfo.maxTemp,
                            minTemp = forecast.forecastInfo.minTemp,
                            icon = forecast.forecastInfo.weatherIcon,
                            precipitation = forecast.forecastInfo.precipitation
                        )
                    )
                }
            }else {
                _forecastListItem.add(EmptyItem())
            }
            forecastList.postValue(_forecastListItem.toList())
        }
    }
}
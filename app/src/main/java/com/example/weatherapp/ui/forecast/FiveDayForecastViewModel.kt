package com.example.weatherapp.ui.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.dto.ForecastDto
import com.example.weatherapp.data.model.entity.Weather
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiveDayForecastViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val weather: MutableLiveData<Weather> by lazy {
        MutableLiveData<Weather>()
    }

    val weatherList = MutableLiveData<List<ForecastDto>>()


    fun getFiveDayWeatherForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            val result = repository.fetchFiveDayWeatherForecast(lat, lon)

            result.body()?.let {
                weatherList.postValue(clearList(it.list))
            }
        }
    }

    /**
     * Forecast API return 5-day forecast with 3-hour step for each day, so this makes the list longer.
     * This fun makes sure to clear the list and only return single 5-day forecast.
     * @param list
     * @return ArrayList
     * */
    private fun clearList(list: List<ForecastDto>): List<ForecastDto> {
        val cityMap = mutableMapOf<String, ForecastDto>()

        list.forEach{city ->
            val date = city.dtTxt?.split(" ")?.get(0) ?: ""
            if(!cityMap.containsKey(date)){
                cityMap[date] = city
            }
        }

        return cityMap.values.toList()
    }
}
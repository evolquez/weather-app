package com.example.weatherapp.ui.forecast.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.dto.CityWeatherDto
import com.example.weatherapp.data.model.dto.CoordinateDto
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiveDayForecastViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    val cityWeather: MutableLiveData<CityWeatherDto> by lazy {
        MutableLiveData<CityWeatherDto>()
    }

    val weatherList = MutableLiveData<List<CityWeatherDto>>()


    fun getFiveDayWeatherForecast(cityCoordinate: CoordinateDto) {
        viewModelScope.launch {
            val result = repository.fetchFiveDayWeatherForecast(cityCoordinate)

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
    private fun clearList(list: List<CityWeatherDto>): List<CityWeatherDto> {
        val cityMap = mutableMapOf<String, CityWeatherDto>()

        list.forEach{city ->
            val date = city.dtTxt.split(" ")[0]
            if(!cityMap.containsKey(date)){
                cityMap[date] = city
            }
        }

        return cityMap.values.toList()
    }
}
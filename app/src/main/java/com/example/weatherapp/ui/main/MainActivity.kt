package com.example.weatherapp.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.forecast.FiveDayForecastActivity
import com.example.weatherapp.ui.main.di.MainComponent
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.data.model.dto.CityWeatherInfoDto
import com.example.weatherapp.ui.common.holder.item.WeatherItem
import com.google.android.material.divider.MaterialDividerItemDecoration
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View {

    lateinit var component: MainComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        component = (applicationContext as WeatherApplication)
            .appComponent
            .mainComponent()
            .create()

        component.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initialize()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initialize() {
        val mainAdapter = MainAdapter(this)

        with(binding.recyclerViewForecast) {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
            addItemDecoration(MaterialDividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }

        viewModel.getWeatherData()

        viewModel.weatherList.observe(this) { list ->
            if(list != null){
                mainAdapter.items = list
                mainAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun start5DaysForecast(weatherItem: WeatherItem) {
        startActivity(
            FiveDayForecastActivity.createIntent(this,
                CityWeatherInfoDto(
                    cityId = weatherItem.cityId,
                    cityName = weatherItem.city,
                    country = weatherItem.countryCode,
                    cityLat = weatherItem.cityLat,
                    cityLon = weatherItem.cityLon
                )
            )
        )
    }
}

interface View {
    fun initialize()
    fun start5DaysForecast(weatherItem: WeatherItem)
}
package com.example.weatherapp.ui.forecast.forecast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.model.entity.Weather
import com.example.weatherapp.databinding.ActivityFiveDayForecastBinding
import com.example.weatherapp.ui.forecast.forecast.di.FiveDayForecastComponent
import com.example.weatherapp.util.Util.getSerializable
import com.example.weatherapp.WeatherApplication
import com.google.android.material.divider.MaterialDividerItemDecoration
import javax.inject.Inject

class FiveDayForecastActivity : AppCompatActivity(), View {

    lateinit var component: FiveDayForecastComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FiveDayForecastViewModel> { viewModelFactory }

    private lateinit var binding: ActivityFiveDayForecastBinding
    private var weather: Weather? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        component = (applicationContext as WeatherApplication).appComponent.fiveDayForecastComponent().create()
        component.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityFiveDayForecastBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        weather = intent.getSerializable(WEATHER_PARAM, Weather::class.java)

        weather?.let {
            viewModel.weather.value = it
            initialize()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initialize() {
        val forecastAdapter = FiveDayForecastAdapter(this)

        viewModel.weather.value?.let {
            binding.textViewCityName.text = getString(R.string.city_name_format, it.cityName, it.countryCode)

            viewModel.getFiveDayWeatherForecast(it.cityLat, it.cityLon)
        }

        with(binding.recyclerViewForecast) {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@FiveDayForecastActivity)
            adapter = forecastAdapter
            addItemDecoration(MaterialDividerItemDecoration(this@FiveDayForecastActivity, LinearLayoutManager.VERTICAL))
        }

        viewModel.weatherList.observe(this) {list ->
            forecastAdapter.items = list
            forecastAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val WEATHER_PARAM = "weather_param"
    }
}

interface View {
    fun initialize()
}
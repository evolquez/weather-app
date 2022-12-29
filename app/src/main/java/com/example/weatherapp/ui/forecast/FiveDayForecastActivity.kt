package com.example.weatherapp.ui.forecast

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityFiveDayForecastBinding
import com.example.weatherapp.util.Util.getSerializable
import com.example.weatherapp.WeatherApplication
import com.example.weatherapp.data.model.dto.CityWeatherInfoDto
import com.example.weatherapp.ui.forecast.di.FiveDayForecastComponent
import com.google.android.material.divider.MaterialDividerItemDecoration
import javax.inject.Inject

class FiveDayForecastActivity : AppCompatActivity(), View {

    lateinit var component: FiveDayForecastComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FiveDayForecastViewModel> { viewModelFactory }

    private lateinit var binding: ActivityFiveDayForecastBinding
    private var cityWeatherInfo: CityWeatherInfoDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        component = (applicationContext as WeatherApplication)
            .appComponent
            .fiveDayForecastComponent()
            .create()

        component.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityFiveDayForecastBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cityWeatherInfo = intent.getSerializable(WEATHER_INFO_PARAM, CityWeatherInfoDto::class.java)

        cityWeatherInfo?.let {
            viewModel.weatherInfo.value = it
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

        with(binding.recyclerViewForecast) {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@FiveDayForecastActivity)
            adapter = forecastAdapter
            addItemDecoration(MaterialDividerItemDecoration(this@FiveDayForecastActivity, LinearLayoutManager.VERTICAL))
        }

        viewModel.weatherInfo.observe(this) {
            binding.textViewCityName.text = getString(R.string.city_name_format, it.cityName, it.country)

            viewModel.getFiveDayWeatherForecast(it.cityId, it.cityLat, it.cityLon)
        }

        viewModel.forecastList.observe(this) {items ->
            forecastAdapter.items = items
            forecastAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val WEATHER_INFO_PARAM = "weather_info_param"

        fun createIntent(
            context: Context,
            weatherInfoDto: CityWeatherInfoDto ): Intent {
            return Intent(context, FiveDayForecastActivity::class.java)
                .putExtra(WEATHER_INFO_PARAM, weatherInfoDto)
        }
    }
}

interface View {
    fun initialize()
}
package com.example.weatherapp.ui.forecast.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.entity.ForecastInfo
import com.example.weatherapp.data.model.entity.Weather
import com.example.weatherapp.databinding.RowCityWeatherBinding
import com.example.weatherapp.util.Util
import com.squareup.picasso.Picasso
import java.util.Date
import javax.inject.Inject

class MainAdapter(
    private val activity: MainActivity
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    @Inject
    lateinit var picasso: Picasso

    init {
        activity.component.inject(this)
    }

    var items: List<Weather> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return ViewHolder(
            RowCityWeatherBinding.inflate(LayoutInflater.from(context),
                parent,
                false),
            activity , picasso)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: RowCityWeatherBinding,
        private val activity: MainActivity,
        private val picasso: Picasso
    ): RecyclerView.ViewHolder(binding.root) {

        private var _weather: Weather? = null

        init {
            binding.root.setOnClickListener{
                _weather?.let { w ->
                    activity.start5DaysForecast(w)
                }
            }
        }

        fun bind(weather: Weather) {

            _weather = weather

            val foreCastInfo: ForecastInfo = weather.forecastInfo

            with(binding) {
                textViewCity.text =  activity.getString(R.string.city_name_format, weather.cityName, weather.countryCode)
                textViewTemperature.text = activity.getString(R.string.temperature, foreCastInfo.currentTemp.toInt())
                textViewHigh.text = activity.getString(R.string.temperature_higher, foreCastInfo.maxTemp.toInt())
                textViewLow.text = activity.getString(R.string.temperature_lower, foreCastInfo.minTemp.toInt())

                //TODO Pending to calc or get precipitation (Missing from api endpoint)
                textViewPrecipitation.text = activity.getString(R.string.precipitation_percent, "--")

                val dateInMillis = (weather.date * 1000) + (weather.countryTimeZone * 1000)
                textViewTimestamp.text = Date(dateInMillis).toString()

                val imageUrl = String.format(Util.IMAGE_URL_FORMAT, foreCastInfo.weatherIcon)
                picasso.load(imageUrl).into(imageViewWeather)
            }
        }
    }
}
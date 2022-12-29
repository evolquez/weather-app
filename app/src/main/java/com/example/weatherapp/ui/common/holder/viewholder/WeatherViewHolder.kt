package com.example.weatherapp.ui.common.holder.viewholder

import com.example.weatherapp.R
import com.example.weatherapp.databinding.RowCityWeatherBinding
import com.example.weatherapp.ui.common.holder.item.WeatherItem
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.util.Util
import com.squareup.picasso.Picasso
import java.util.*

class WeatherViewHolder(
    binding: RowCityWeatherBinding,
    private val activity: MainActivity,
    private val picasso: Picasso
): BaseViewHolder<WeatherItem, RowCityWeatherBinding>(binding) {

    private var _weatherItem: WeatherItem? = null

    init {
        binding.root.setOnClickListener{
            _weatherItem?.let { w ->
                activity.start5DaysForecast(w)
            }
        }
    }

    override fun bind(item: WeatherItem) {

        _weatherItem = item

        with(binding) {
            textViewCity.text =  activity.getString(R.string.city_name_format, item.city, item.countryCode)
            textViewTemperature.text = activity.getString(R.string.temperature, item.currentTemp.toInt())
            textViewHigh.text = activity.getString(R.string.temperature_higher, item.maxTemp.toInt())
            textViewLow.text = activity.getString(R.string.temperature_lower, item.minTemp.toInt())

            //TODO Pending to calc or get precipitation percentage. (Field is missing from the API endpoint)
            textViewPrecipitation.text = activity.getString(R.string.precipitation_percent, "--")

            val dateInMillis = (item.dateInMillis * 1000) + (item.countryTimeZone * 1000)
            textViewTimestamp.text = Date(dateInMillis).toString()

            val imageUrl = String.format(Util.IMAGE_URL_FORMAT, item.icon)
            picasso.load(imageUrl).into(imageViewWeather)
        }
    }
}
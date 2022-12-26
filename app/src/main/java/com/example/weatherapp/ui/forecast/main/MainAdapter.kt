package com.example.weatherapp.ui.forecast.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.dto.CityWeatherDto
import com.example.weatherapp.databinding.RowCityWeatherBinding
import com.squareup.picasso.Picasso
import java.util.Date

class MainAdapter(
    private val context: Context
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var items: List<CityWeatherDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowCityWeatherBinding.inflate(LayoutInflater.from(context), parent, false), context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: RowCityWeatherBinding,
        private val context: Context): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CityWeatherDto) {

            with(binding) {
                textViewCity.text =  context.getString(R.string.city_name_format, item.name, item.sys.country)
                textViewTemperature.text = context.getString(R.string.temperature, item.main.temp.toInt())
                textViewHigh.text = context.getString(R.string.temperature_higher, item.main.tempMax.toInt())
                textViewLow.text = context.getString(R.string.temperature_lower, item.main.tempMin.toInt())

                //TODO Pending to calc or get precipitation
                textViewPrecipitation.text = context.getString(R.string.precipitation_percent, "--")


                textViewTimestamp.text = Date((item.dt * 1000) + (item.sys.timezone * 1000)).toString()

                Picasso.get()
                    .load(String.format(IMAGE_URL_FORMAT, item.weather[0].icon))
                    .into(imageViewWeather)
            }
        }
    }

    companion object {
        const val IMAGE_URL_FORMAT = "https://openweathermap.org/img/wn/%s@2x.png"
    }
}
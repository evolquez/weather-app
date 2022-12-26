package com.example.weatherapp.ui.forecast.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.dto.CityWeatherDto
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

    var items: List<CityWeatherDto> = emptyList()

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

        private var cityWeather: CityWeatherDto? = null

        init {
            binding.root.setOnClickListener{
                cityWeather?.let {
                    activity.start5DaysForecast(it)
                }
            }
        }

        fun bind(item: CityWeatherDto) {

            cityWeather = item

            with(binding) {
                textViewCity.text =  activity.getString(R.string.city_name_format, item.name, item.sys.country)
                textViewTemperature.text = activity.getString(R.string.temperature, item.main.temp.toInt())
                textViewHigh.text = activity.getString(R.string.temperature_higher, item.main.tempMax.toInt())
                textViewLow.text = activity.getString(R.string.temperature_lower, item.main.tempMin.toInt())

                //TODO Pending to calc or get precipitation (Missing from api endpoint)
                textViewPrecipitation.text = activity.getString(R.string.precipitation_percent, "--")

                val dateInMillis = (item.dt * 1000) + (item.sys.timezone * 1000)
                textViewTimestamp.text = Date(dateInMillis).toString()

                val imageUrl = String.format(Util.IMAGE_URL_FORMAT, item.weather[0].icon)
                picasso.load(imageUrl).into(imageViewWeather)
            }
        }
    }
}
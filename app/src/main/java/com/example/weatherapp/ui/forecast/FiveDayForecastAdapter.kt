package com.example.weatherapp.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.entity.Forecast
import com.example.weatherapp.databinding.RowDayForecastBinding
import com.example.weatherapp.util.Util
import com.squareup.picasso.Picasso
import javax.inject.Inject

class FiveDayForecastAdapter(private val activity: FiveDayForecastActivity): RecyclerView.Adapter<FiveDayForecastAdapter.ViewHolder>() {

    @Inject
    lateinit var picasso: Picasso

    init {
        activity.component.inject(this)
    }

    var items: List<Forecast> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return ViewHolder(
            RowDayForecastBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            activity, picasso
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: RowDayForecastBinding,
        private val activity: FiveDayForecastActivity,
        private val picasso: Picasso
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Forecast) {
            val forecastInfo = item.forecastInfo

            with(binding) {
                textViewDate.text = forecastInfo.dateText?.split(" ")?.get(0) ?: ""
                textViewMaxTemp.text = activity.getString(R.string.temperature, forecastInfo.maxTemp.toInt())
                val min = activity.getString(R.string.temperature, forecastInfo.minTemp.toInt())
                textViewMinTemp.text = " / ${min}"
                val precipitation = (forecastInfo.precipitation * 100).toInt()
                textViewPrecipitation.text = activity.getString(R.string.precipitation_percent, precipitation.toString())

                val imageUrl = String.format(Util.IMAGE_URL_FORMAT, forecastInfo.weatherIcon)
                picasso.load(imageUrl).into(imageViewWeather)
            }
        }
    }
}
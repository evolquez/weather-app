package com.example.weatherapp.ui.forecast.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.dto.ForecastDto
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

    var items: List<ForecastDto> = emptyList()

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

        fun bind(item: ForecastDto) {

            with(binding) {
                textViewDate.text = item.dtTxt?.split(" ")?.get(0) ?: ""
                textViewMaxTemp.text = activity.getString(R.string.temperature, item.main.tempMax.toInt())
                val min = activity.getString(R.string.temperature, item.main.tempMin.toInt())
                textViewMinTemp.text = " / ${min}"
                val precipitation = (item.pop * 100).toInt()
                textViewPrecipitation.text = activity.getString(R.string.precipitation_percent, precipitation.toString())

                val imageUrl = String.format(Util.IMAGE_URL_FORMAT, item.weather[0].icon)
                picasso.load(imageUrl).into(imageViewWeather)
            }
        }
    }
}
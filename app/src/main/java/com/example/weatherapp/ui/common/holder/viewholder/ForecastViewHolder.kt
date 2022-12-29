package com.example.weatherapp.ui.common.holder.viewholder

import android.annotation.SuppressLint
import com.example.weatherapp.R
import com.example.weatherapp.databinding.RowDayForecastBinding
import com.example.weatherapp.ui.common.holder.item.ForecastItem
import com.example.weatherapp.ui.forecast.FiveDayForecastActivity
import com.example.weatherapp.util.Util
import com.squareup.picasso.Picasso

class ForecastViewHolder(
    binding: RowDayForecastBinding,
    private val activity: FiveDayForecastActivity,
    private val picasso: Picasso
): BaseViewHolder<ForecastItem, RowDayForecastBinding>(binding) {

    @SuppressLint("SetTextI18n")
    override fun bind(item: ForecastItem) {
        with(binding) {
            textViewDate.text = item.date
            textViewMaxTemp.text = activity.getString(R.string.temperature, item.maxTemp.toInt())
            val min = activity.getString(R.string.temperature, item.minTemp.toInt())
            textViewMinTemp.text = " / $min"
            textViewPrecipitation.text = activity.getString(R.string.precipitation_percent, item.precipitationPercent.toString())
            val imageUrl = String.format(Util.IMAGE_URL_FORMAT, item.icon)
            picasso.load(imageUrl).into(imageViewWeather)
        }
    }
}
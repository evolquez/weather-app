package com.example.weatherapp.ui.common.holder.item

import java.io.Serializable

class WeatherItem(
    val cityId: Int,
    val cityLat: Double,
    val cityLon: Double,
    val city: String,
    val countryCode: String,
    val countryTimeZone: Int,
    val currentTemp: Double,
    val dateInMillis: Long,
    maxTemp: Double,
    minTemp: Double,
    icon: String,
    precipitation: Double
): BaseForecastItem(maxTemp, minTemp, icon, precipitation, ItemType.Weather), Serializable

class ForecastItem(
    val date: String,
    maxTemp: Double,
    minTemp: Double,
    icon: String,
    precipitation: Double): BaseForecastItem(maxTemp, minTemp, icon, precipitation, ItemType.Forecast)

abstract class BaseForecastItem(
    val maxTemp: Double,
    val minTemp: Double,
    val icon: String,
    precipitation: Double,
    itemType: ItemType
): BaseItem(itemType) {
    val precipitationPercent = (precipitation * 100).toInt()
}

class LoaderItem: BaseItem(ItemType.Loader)
class EmptyItem: BaseItem(ItemType.Empty)

abstract class BaseItem(val itemType: ItemType)
package com.example.weatherapp.ui.common.holder.item

enum class ItemType(val value: Int) {
    Weather(0),
    Forecast(1),
    Loader(2),
    Empty(3);

    companion object {
        fun fromValue(value: Int): ItemType? = values().firstOrNull { value == it.value }
    }
}
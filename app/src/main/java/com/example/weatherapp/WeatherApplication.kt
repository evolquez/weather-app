package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent

open class WeatherApplication: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
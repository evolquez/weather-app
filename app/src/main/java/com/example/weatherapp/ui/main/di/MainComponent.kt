package com.example.weatherapp.ui.main.di

import com.example.weatherapp.di.ActivityScope
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.main.MainAdapter
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(adapter: MainAdapter)
}
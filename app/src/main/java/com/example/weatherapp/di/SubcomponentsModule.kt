package com.example.weatherapp.di

import com.example.weatherapp.ui.forecast.main.di.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class])
class SubcomponentsModule
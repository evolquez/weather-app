package com.example.weatherapp.ui.forecast.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.forecast.main.di.MainComponent
import com.example.weatherapp.util.WeatherApplication
import com.google.android.material.divider.MaterialDividerItemDecoration
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var component: MainComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        component = (applicationContext as WeatherApplication)
            .appComponent
            .mainComponent()
            .create()

        component.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mainAdapter = MainAdapter(this)

        with(binding.recyclerViewForecast) {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
            addItemDecoration(MaterialDividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }

        viewModel.getWeatherData()

        viewModel.weatherList.observe(this) { list ->
            if(list != null){
                mainAdapter.items = list
                mainAdapter.notifyDataSetChanged()
            }
        }
    }
}
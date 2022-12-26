package com.example.weatherapp.ui.forecast.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
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
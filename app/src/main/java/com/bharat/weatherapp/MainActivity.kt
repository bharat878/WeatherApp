package com.bharat.weatherapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bharat.weatherapp.R
import com.bharat.weatherapp.repository.WeatherRepository
import com.bharat.weatherapp.viewmodel.WeatherViewModel
import com.bharat.weatherapp.viewmodel.WeatherViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = WeatherRepository()
        weatherViewModel = ViewModelProvider(this, WeatherViewModelFactory(repository,
            applicationContext as Application
        )).get(WeatherViewModel::class.java)

        observeData()

    }

    private fun observeData() {
        weatherViewModel.getCurrentWeatherDetails().observe(this, Observer {
            Log.d("current temp ", it.toString())
        })

        weatherViewModel.getWeatherForecastDetails().observe(this, Observer {
            Log.d("weather Forecast ", it.toString())
        })
    }
}
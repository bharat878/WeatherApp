package com.bharat.weatherapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bharat.weatherapp.repository.WeatherRepository

class WeatherViewModelFactory(private val repository: WeatherRepository, private val context: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(context, repository) as T
    }
}
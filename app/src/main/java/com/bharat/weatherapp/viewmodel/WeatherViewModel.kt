package com.bharat.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharat.weatherapp.model.CurrentWeatherResponse
import com.bharat.weatherapp.model.WeatherForecastResponse
import com.bharat.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val context: Application, private val repository: WeatherRepository): ViewModel() {

    private val currentWeatherData by lazy {
        MutableLiveData<CurrentWeatherResponse>()
    }

    private val weatherForecastData by lazy {
        MutableLiveData<WeatherForecastResponse>()
    }

    fun getCurrentWeatherDetails(): LiveData<CurrentWeatherResponse> = currentWeatherData

    fun getWeatherForecastDetails(): LiveData<WeatherForecastResponse> = weatherForecastData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherDetail()
        }
    }

    private fun getWeatherDetail() {
        repository.getWeatherDetails {
            currentWeatherData.postValue(it)
        }

        repository.getWeatherForecast {
            weatherForecastData.postValue(it)
        }
    }
}
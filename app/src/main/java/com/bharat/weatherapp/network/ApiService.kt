package com.bharat.weatherapp.network

import com.bharat.weatherapp.model.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("weather?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
    fun getCurrentWeather(): Call<CurrentWeatherResponse>

    @GET("weather?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
    fun getWeatherForecast(): Call<CurrentWeatherResponse>
}
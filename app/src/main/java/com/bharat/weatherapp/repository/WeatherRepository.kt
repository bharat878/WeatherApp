package com.bharat.weatherapp.repository

import com.bharat.weatherapp.model.CurrentWeatherResponse
import com.bharat.weatherapp.model.WeatherForecastResponse
import com.bharat.weatherapp.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class WeatherRepository() {
    fun getWeatherDetails(callback: (CurrentWeatherResponse?) -> Unit) {
        val call: Call<CurrentWeatherResponse> =
            RetrofitService.getOrCreateAndGetService().getCurrentWeather()

        call.enqueue(object : retrofit2.Callback<CurrentWeatherResponse?> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse?>,
                response: Response<CurrentWeatherResponse?>
            ) {
                if (response.body() != null)
                    callback(response.body()!!)
                else
                    callback(null)

            }

            override fun onFailure(call: Call<CurrentWeatherResponse?>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getWeatherForecast(callback: (WeatherForecastResponse?) -> Unit) {
        val call: Call<WeatherForecastResponse?> =
            RetrofitService.getOrCreateAndGetService().getWeatherForecast()

        call.enqueue(object : retrofit2.Callback<WeatherForecastResponse?> {
            override fun onResponse(
                call: Call<WeatherForecastResponse?>,
                response: Response<WeatherForecastResponse?>
            ) {
                if (response.body() != null)
                    callback(response.body())
                else
                    callback(null)

            }

            override fun onFailure(call: Call<WeatherForecastResponse?>, t: Throwable) {
                callback(null)
            }
        })
    }

}
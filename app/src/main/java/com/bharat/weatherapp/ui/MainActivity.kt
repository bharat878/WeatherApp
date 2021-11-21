package com.bharat.weatherapp.ui

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bharat.weatherapp.R
import com.bharat.weatherapp.databinding.ActivityMainBinding
import com.bharat.weatherapp.repository.WeatherRepository
import com.bharat.weatherapp.utils.DialogProgress
import com.bharat.weatherapp.viewmodel.WeatherViewModel
import com.bharat.weatherapp.viewmodel.WeatherViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogProgress: DialogProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = WeatherRepository()
        weatherViewModel = ViewModelProvider(this, WeatherViewModelFactory(repository,
            applicationContext as Application
        )).get(WeatherViewModel::class.java)

        initUI()
        observeData()
        onClick()
    }

    private fun onClick() {
        binding.btnTry.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initUI() {
        dialogProgress = DialogProgress(this)
        dialogProgress.showProgress()
    }

    private fun observeData() {
        weatherViewModel.getCurrentWeatherDetails().observe(this, Observer {

            when {
                it == null -> {
                    dialogProgress.cancelProgress()
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.layoutSuccess.visibility = View.GONE
                }

                it.cod == 200 ->{
                    dialogProgress.cancelProgress()
                    binding.txtCity.text = it.name

                    val temp = it.main?.temp
                    if (temp != null) {
                        binding.txtTemp.text = (temp - 273.15).toInt().toString()
                    }
                    initWeatherForecast()

                    Log.d("current temp ", it.toString())
                }

                it.cod == 400 -> {
                    dialogProgress.cancelProgress()
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.layoutSuccess.visibility = View.GONE
                }
            }

        })

    }

    private fun initWeatherForecast() {
        val fragment = WeatherForecastFragment()
        fragment.show(supportFragmentManager, "bottom")
    }

}
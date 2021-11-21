package com.bharat.weatherapp.ui

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bharat.weatherapp.R
import com.bharat.weatherapp.databinding.FragmentWeatherForecastBinding
import com.bharat.weatherapp.model.WeatherForecastResponse
import com.bharat.weatherapp.repository.WeatherRepository
import com.bharat.weatherapp.ui.adapter.WeatherForecastAdapter
import com.bharat.weatherapp.viewmodel.WeatherViewModel
import com.bharat.weatherapp.viewmodel.WeatherViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WeatherForecastFragment : BottomSheetDialogFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var binding: FragmentWeatherForecastBinding
    private lateinit var mContext: Context
    private val weatherList: MutableList<WeatherForecastResponse.ListITem?> = mutableListOf()
    private val finalWeatherList: MutableList<WeatherForecastResponse.ListITem?> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_forecast, container, false)

        val repository = WeatherRepository()
        weatherViewModel = ViewModelProvider(
            this, WeatherViewModelFactory(
                repository,
                activity?.applicationContext as Application
            )
        ).get(WeatherViewModel::class.java)

        observeData()

        return binding.root
    }

    private fun observeData() {
        weatherViewModel.getWeatherForecastDetails().observe(viewLifecycleOwner, Observer {
            weatherList.addAll(it.list!!)
            Log.d("weather Forecast ", it.toString())
            initUI()
        })

    }

    private fun initUI() {
        val mLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        binding.rvWeather.smoothScrollToPosition(RecyclerView.VERTICAL)
        binding.rvWeather.layoutManager = mLayoutManager

        var i = 0
        weatherList.forEach {
            if (i < 8) {
                if (i % 2 == 0)
                    finalWeatherList.add(it)
            }
            i++
        }

        val adapter = WeatherForecastAdapter(mContext, finalWeatherList)
        binding.rvWeather.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeatherForecastFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
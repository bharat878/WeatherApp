package com.bharat.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bharat.weatherapp.R
import com.bharat.weatherapp.databinding.ItemWeatherForecastBinding
import com.bharat.weatherapp.model.WeatherForecastResponse
import com.google.gson.internal.bind.util.ISO8601Utils.format
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class WeatherForecastAdapter(
    private val context: Context,
    private val weatherList: MutableList<WeatherForecastResponse.ListITem?>
) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastHolder>() {

    inner class WeatherForecastHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var binding: ItemWeatherForecastBinding
        fun setData(data: WeatherForecastResponse.ListITem?) {
            binding = ItemWeatherForecastBinding.bind(itemView)

            val dtx = data?.dtTxt
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

            var date: Date = dateFormat.parse(dtx)
            val dayOfWeek = android.text.format.DateFormat.format("EEEE", date)
            binding.txtDay.text = dayOfWeek
            binding.txtTemp.text = (data?.main?.temp?.minus(273.15))?.toInt().toString() + " C"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_weather_forecast, parent, false)
        return WeatherForecastHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherForecastHolder, position: Int) {
        val data = weatherList[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}
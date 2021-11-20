package com.bharat.weatherapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CurrentWeatherResponse(
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("cod")
    var cod: Int? = null,
    @SerializedName("coord")
    var coord: Coord? = null,
    @SerializedName("dt")
    var dt: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("sys")
    var sys: Sys? = null,
    @SerializedName("timezone")
    var timezone: Int? = null,
    @SerializedName("visibility")
    var visibility: Int? = null,
    @SerializedName("weather")
    var weather: List<Weather?>? = null,
    @SerializedName("wind")
    var wind: Wind? = null
) : Parcelable {
    @Parcelize
    data class Clouds(
        @SerializedName("all")
        var all: Int? = null
    ) : Parcelable

    @Parcelize
    data class Coord(
        @SerializedName("lat")
        var lat: Double? = null,
        @SerializedName("lon")
        var lon: Double? = null
    ) : Parcelable

    @Parcelize
    data class Main(
        @SerializedName("feels_like")
        var feelsLike: Double? = null,
        @SerializedName("humidity")
        var humidity: Int? = null,
        @SerializedName("pressure")
        var pressure: Int? = null,
        @SerializedName("temp")
        var temp: Double? = null,
        @SerializedName("temp_max")
        var tempMax: Double? = null,
        @SerializedName("temp_min")
        var tempMin: Double? = null
    ) : Parcelable

    @Parcelize
    data class Sys(
        @SerializedName("country")
        var country: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("sunrise")
        var sunrise: Int? = null,
        @SerializedName("sunset")
        var sunset: Int? = null,
        @SerializedName("type")
        var type: Int? = null
    ) : Parcelable

    @Parcelize
    data class Weather(
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("icon")
        var icon: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("main")
        var main: String? = null
    ) : Parcelable

    @Parcelize
    data class Wind(
        @SerializedName("deg")
        var deg: Int? = null,
        @SerializedName("speed")
        var speed: Double? = null
    ) : Parcelable
}
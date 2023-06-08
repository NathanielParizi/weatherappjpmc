package com.nathaniel.weatherapp.data.remote.model.weather


import androidx.annotation.Keep

@Keep
data class WeatherResponse(
    val base: String?,
    val clouds: com.nathaniel.weatherapp.data.remote.model.weather.Clouds?,
    val cod: Int?,
    val coord: com.nathaniel.weatherapp.data.remote.model.weather.Coord?,
    val dt: Int?,
    val id: Int?,
    val main: com.nathaniel.weatherapp.data.remote.model.weather.Main?,
    val name: String?,
    val sys: com.nathaniel.weatherapp.data.remote.model.weather.Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<com.nathaniel.weatherapp.data.remote.model.weather.Weather?>?,
    val wind: com.nathaniel.weatherapp.data.remote.model.weather.Wind?
)
package com.nathaniel.weatherapp.data.repo


import com.nathaniel.weatherapp.data.remote.model.geo_location.GeoLocationResponse
import com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse
import retrofit2.Response

interface Repository {

    suspend fun getLatLong(query: String): Response<com.nathaniel.weatherapp.data.remote.model.geo_location.GeoLocationResponse?>

    suspend fun getWeatherByLatLong(lat: Double,lon: Double): Response<com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse?>


}
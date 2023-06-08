package com.nathaniel.weatherapp.data.remote.api

import com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse
import com.nathaniel.weatherapp.data.remote.model.geo_location.GeoLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {


    @GET(com.nathaniel.weatherapp.data.remote.api.ApiDetails.GEO)
    suspend fun getLatLong(@Query("q") cityName: String): Response<com.nathaniel.weatherapp.data.remote.model.geo_location.GeoLocationResponse?>

    @GET(com.nathaniel.weatherapp.data.remote.api.ApiDetails.WEATHER)
    suspend fun getWeatherByLatLong(@Query("lat") lat: Double,@Query("lon") lon: Double): Response<com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse?>

}
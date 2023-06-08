package com.nathaniel.weatherapp.data.repo

import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: com.nathaniel.weatherapp.data.remote.api.ApiRequest
) : Repository {

    override suspend fun getLatLong(query: String)=apiRequest.getLatLong(query)

    override suspend fun getWeatherByLatLong(lat: Double, lon: Double): Response<com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse?> =apiRequest.getWeatherByLatLong(lat,lon)


}
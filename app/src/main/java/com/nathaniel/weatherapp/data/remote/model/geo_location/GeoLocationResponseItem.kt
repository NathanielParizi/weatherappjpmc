package com.nathaniel.weatherapp.data.remote.model.geo_location


import androidx.annotation.Keep

@Keep
data class GeoLocationResponseItem(
    val country: String?,
    val lat: Double?,
    val local_names: com.nathaniel.weatherapp.data.remote.model.geo_location.LocalNames?,
    val lon: Double?,
    val name: String?,
    val state: String?
)
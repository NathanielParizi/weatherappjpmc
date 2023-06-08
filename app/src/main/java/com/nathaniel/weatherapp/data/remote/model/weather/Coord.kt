package com.nathaniel.weatherapp.data.remote.model.weather


import androidx.annotation.Keep

@Keep
data class Coord(
    val lat: Double?,
    val lon: Double?
)
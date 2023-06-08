package com.nathaniel.weatherapp.data.remote.model.weather


import androidx.annotation.Keep

@Keep
data class Wind(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)
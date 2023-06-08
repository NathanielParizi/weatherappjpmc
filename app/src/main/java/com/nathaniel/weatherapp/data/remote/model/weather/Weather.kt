package com.nathaniel.weatherapp.data.remote.model.weather


import androidx.annotation.Keep

@Keep
data class Weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)
package com.nathaniel.weatherapp.util

import androidx.compose.ui.unit.dp
import com.android.weatherapp.R
import java.text.SimpleDateFormat
import java.util.Date

object Converter {
    fun timeConverter(time: Long): String {
        val converter = SimpleDateFormat("hh:mm a")

        return converter.format(Date(time * 1000))
    }

    fun setLayoutBackgroundImage(url: String):Int{
        return when(url){
            "01d","02d","03d","04d","09d","10d","11d","13d","50d" -> {
                R.drawable.ic_background_daylight
            }

            "01n","02n","03n","04n","09n","10n","11n","13n","50n" -> {
                R.drawable.ic_background_night
            }

            else-> R.drawable.ic_background_daylight
        }

    }

    val TOP_APP_BAR_HEIGHT = 56.dp


    fun setImageResource( url:String):Int{
        when(url){
            "01d" -> return R.drawable.ic_01d
            "01n" -> return R.drawable.ic_01n
            "02d" -> return R.drawable.ic_02d
            "02n" -> return R.drawable.ic_02n
            "03d" -> return R.drawable.ic_03d
            "03n" -> return R.drawable.ic_03n
            "04d" -> return R.drawable.ic_04d
            "04n" -> return R.drawable.ic_04n
            "09d" -> return R.drawable.ic_09d
            "09n" -> return R.drawable.ic_09n
            "10d" -> return R.drawable.ic_10d
            "10n" -> return R.drawable.ic_10n
            "11d" -> return R.drawable.ic_11d
            "11n" -> return R.drawable.ic_11n
            "13d" -> return R.drawable.ic_13d
            "13n" -> return R.drawable.ic_13n
            "50d" -> return R.drawable.ic_50d
            "50n" -> return R.drawable.ic_50n
             else -> return R.drawable.ic_01d
        }
    }


    const val CODE_200 = 200
    const val CODE_201 = 201
    const val CODE_401 = 401

}
package com.nathaniel.weatherapp.data.repo

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class RepositoryImplTest {


    @Mock
    lateinit var apiRequest: com.nathaniel.weatherapp.data.remote.api.ApiRequest

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }




    @Test
    fun getWeatherCorrectCityName() = runTest {
        val weatherResult = com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse(
            "stations",
            clouds = null,
            cod = 0,
            coord = null,
            dt = 0,
            id = 0,
            name = "berlin",
            sys = null,
            timezone = 0,
            visibility = 0,
            wind = null,
            weather = emptyList(),
            main = com.nathaniel.weatherapp.data.remote.model.weather.Main(
                36.64,
                982,
                41,
                1004,
                1004,
                34.53,
                34.53,
                31.53
            )
        )

        Mockito.`when`(apiRequest.getWeatherByLatLong(51.5073219,-0.1276474)).thenReturn(Response.success(weatherResult))


        val sut = RepositoryImpl(apiRequest)
        val result = sut.getWeatherByLatLong(51.5073219,-0.1276474)
        Assert.assertEquals("berlin", result.body()!!.name)
    }

    @Test
    fun getWeatherMaxTempIsGreaterThanMinTemp() = runTest {
        val weatherResult = com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse(
            "stations",
            clouds = null,
            cod = 0,
            coord = null,
            dt = 0,
            id = 0,
            name = "berlin",
            sys = null,
            timezone = 0,
            visibility = 0,
            wind = null,
            weather = emptyList(),
            main = com.nathaniel.weatherapp.data.remote.model.weather.Main(
                36.64,
                982,
                41,
                1004,
                1004,
                34.53,
                34.53,
                31.53
            )
        )

        Mockito.`when`(apiRequest.getWeatherByLatLong(51.5073219,-0.1276474)).thenReturn(Response.success(weatherResult))


        val sut = RepositoryImpl(apiRequest)
        val result =  sut.getWeatherByLatLong(51.5073219,-0.1276474)
        val check = result.body()!!.main?.temp_max?.toInt()!! > result.body()!!.main?.temp_min?.toInt()!!
        Assert.assertEquals(true, check)
    }


}
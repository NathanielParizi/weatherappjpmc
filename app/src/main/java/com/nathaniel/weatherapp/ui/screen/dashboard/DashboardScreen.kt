package com.nathaniel.weatherapp.ui.screen.dashboard

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.weatherapp.R
import com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse
import com.nathaniel.weatherapp.ui.MainViewModel
import com.nathaniel.weatherapp.ui.composable.SearchAppBar
import com.nathaniel.weatherapp.ui.displayToast
import com.nathaniel.weatherapp.ui.theme.DarkBlue
import com.nathaniel.weatherapp.ui.theme.LightGray
import com.nathaniel.weatherapp.ui.theme.VeryLightGray
import com.nathaniel.weatherapp.util.Converter.setImageResource
import com.nathaniel.weatherapp.util.Converter.setLayoutBackgroundImage
import com.nathaniel.weatherapp.util.Converter.timeConverter
import com.nathaniel.weatherapp.util.flow.RequestState
import com.nathaniel.weatherapp.util.flow.RequestState.*

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun DashboardScreen(
    viewModel: MainViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
//    val allTasks by viewModel.weatherData.collectAsState()
    val allTasks by viewModel.weatherData.collectAsState()

    val context = LocalContext.current
    Scaffold(
        topBar = {
            SearchAppBar(
                text = viewModel.searchTextState.value,
                onTextChange = { newText ->
                    viewModel.searchTextState.value = newText
                },
                onSearchClicked = {
                    viewModel.getLatLong(viewModel.searchTextState.value)
                    keyboardController?.hide()
                }
            )
        },
        content = {
            when (allTasks) {
                is Success<*> -> {
                    DashboardScreenUi(allTasks as Success<WeatherResponse>)
                }
                is ErrorMsg -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_connection),
                            contentDescription = "",
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.Center),
                        )
                    }
                    displayToast(
                        context, (allTasks as ErrorMsg).errorMsg.toString()

                    )
                    viewModel.setIdleState()
                }

                else -> {

                }
            }
        }

    )
}


@Composable
private fun DashboardScreenUi(allTasks: Success<WeatherResponse>) {
    Image(
        painter = painterResource(id = setLayoutBackgroundImage(allTasks.data.weather?.get(0)?.icon.toString())),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    WeatherPage(allTasks.data)
}

@Composable
fun WeatherPage(weatherResponse: com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderImage(weatherResponse)
        MainInfo(weatherResponse)
        InfoTable(weatherResponse)
    }
}

@Composable
fun HeaderImage(weatherResponse: com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse) {

    Image(
        painter = painterResource(id = setImageResource(weatherResponse.weather?.get(0)?.icon.toString())),
        contentDescription = null,
        modifier = Modifier.width(200.dp)
    )

}


@Composable
fun MainInfo(weatherResponse: com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse) {

    Log.d("MainViewModel", "temp:::::    ${weatherResponse.main!!.temp?.toInt().toString()}")
    Column(
        modifier = Modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${weatherResponse.main.temp!!.toInt()}°",
            color = DarkBlue,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = weatherResponse.name.toString(),
            color = DarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = weatherResponse.weather!![0]!!.description.toString(),
            color = DarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Day's maximum temp would hover at ${weatherResponse.main.temp_max!!.toInt()}°." +
                    "\nwhile minimum temp is predicted to be ${weatherResponse.main.temp_min!!.toInt()}°.",
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}


@Composable
fun InfoTable(weatherResponse: com.nathaniel.weatherapp.data.remote.model.weather.WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(
                VeryLightGray
            )
    ) {
        Row(Modifier.padding(16.dp)) {
            InfoItem(
                iconRes = R.drawable.ic_humidity,
                title = "Humidity",
                subtitle = "${weatherResponse.main?.humidity.toString()}%",
                modifier = Modifier.weight(
                    1f
                )
            )
            InfoItem(
                iconRes = R.drawable.ic_wind,
                title = "Wind Speed",
                subtitle = "${weatherResponse.wind?.speed} km/h",
                modifier = Modifier.weight(
                    1f
                )
            )
        }
        Divider(color = LightGray, modifier = Modifier.padding(horizontal = 16.dp))
        Row(Modifier.padding(16.dp)) {
            InfoItem(
                iconRes = R.drawable.ic_sun_half,
                title = "Sunrise",
                subtitle = timeConverter((weatherResponse.sys!!.sunrise)!!.toLong()),
                modifier = Modifier.weight(
                    1f
                )
            )
            InfoItem(
                iconRes = R.drawable.ic_sun_half,
                title = "Sunset",
                subtitle = timeConverter((weatherResponse.sys.sunset)!!.toLong()),
                modifier = Modifier.weight(
                    1f
                )
            )
        }
    }
}


@Composable
fun InfoItem(@DrawableRes iconRes: Int, title: String, subtitle: String, modifier: Modifier) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .width(40.dp)
        )
        Column {
            Text(title, color = Color.Gray)
            Text(subtitle, color = DarkBlue, fontWeight = FontWeight.Bold)
        }
    }
}


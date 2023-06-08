package com.nathaniel.weatherapp.ui.screen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard")
}
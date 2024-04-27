package com.nicolas.freegames.navigation

enum class Screen(route: String) {
    ONBOARDING(route = "onBoarding"),
    HOME(route = "home"),
    DETAILS(route = "details"),
}

sealed class NavigationRoute(val path : String) {
    object OnBoarding : NavigationRoute(path ="onBoarding")
    object Home : NavigationRoute(path = "home")
    object Details : NavigationRoute("details")
}
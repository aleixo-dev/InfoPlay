package com.nicolas.freegames.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class InfoPlayNavigationActions(navController: NavHostController) {

    val navigateToHome: () -> Unit = {
        navController.navigate(Route.HOME.name) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
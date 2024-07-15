package com.nicolas.freegames.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicolas.freegames.ui.details.DetailScreen
import com.nicolas.freegames.ui.home.HomeScreen
import com.nicolas.freegames.ui.onboarding.OnBoarding
import com.nicolas.freegames.utils.UserStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun InfoPlayNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination : String = Route.HOME.name
) {

    val navigationActions = remember(navController) {
        InfoPlayNavigationActions(navController)
    }

    val currentContext = LocalContext.current
    val store = UserStore(currentContext)
    val hasAccessed = store.hasAccessedScreen.collectAsState(initial = false)
    val startScreen = if (hasAccessed.value) startDestination else Route.ONBOARDING.name

    NavHost(
        navController = navController,
        startDestination = startScreen,
        modifier = modifier
    ) {
        composable(route = Route.ONBOARDING.name) {
            OnBoarding(
                onClickButton = {
                    CoroutineScope(context = Dispatchers.Default).launch {
                        store.setHasAccessedScreen(hasAccessed = true)
                    }
                    navigationActions.navigateToHome()
                }
            )
        }
        composable(route = Route.HOME.name) {
            BackHandler(true) {}
            HomeScreen(navController = navController)
        }
        composable(
            route = "${Route.DETAILS.name}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.StringType })
        ) {
            val gameId = remember { it.arguments?.getString("gameId") }
            DetailScreen(
                gameId = gameId,
                navController = navController
            )
        }
    }

}
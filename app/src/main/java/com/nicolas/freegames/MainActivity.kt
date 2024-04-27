package com.nicolas.freegames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.ads.MobileAds
import com.nicolas.freegames.navigation.Screen
import com.nicolas.freegames.ui.details.DetailScreen
import com.nicolas.freegames.ui.home.HomeScreen
import com.nicolas.freegames.ui.onboarding.OnBoarding
import com.nicolas.freegames.ui.theme.BackgroundApplication
import com.nicolas.freegames.ui.theme.FreeGamesTheme
import com.nicolas.freegames.utils.UserStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepScreenOpen = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { keepScreenOpen }
        setContent {
            FreeGamesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundApplication
                ) {
                    LaunchedEffect(key1 = Unit) {
                        delay(2000)
                        keepScreenOpen = false
                    }
                    MobileAds.initialize(this)
                    NavControllerScreen()
                }
            }
        }
    }
}

@Composable
fun NavControllerScreen() {
    val navController = rememberNavController()
    val currentContext = LocalContext.current
    val store = UserStore(currentContext)
    val hasAccessed = store.hasAccessedScreen.collectAsState(initial = false)
    val startScreen = if (hasAccessed.value) Screen.HOME.name else Screen.ONBOARDING.name

    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        composable(route = Screen.ONBOARDING.name) {
            OnBoarding(
                onClickButton = {
                    CoroutineScope(context = Dispatchers.Default).launch {
                        store.setHasAccessedScreen(hasAccessed = true)
                    }
                    navController.navigate(Screen.HOME.name)
                }
            )
        }
        composable(route = Screen.HOME.name) {
            BackHandler(true) {}
            HomeScreen(navController = navController)
        }
        composable(
            route = "${Screen.DETAILS.name}/{gameId}",
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

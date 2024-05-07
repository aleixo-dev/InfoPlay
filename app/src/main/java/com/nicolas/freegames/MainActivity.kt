package com.nicolas.freegames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.ads.MobileAds
import com.nicolas.freegames.ui.navigation.InfoPlayNavGraph
import com.nicolas.freegames.ui.theme.BackgroundApplication
import com.nicolas.freegames.ui.theme.FreeGamesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

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
                    InfoPlayNavGraph()
                }
            }
        }
    }
}
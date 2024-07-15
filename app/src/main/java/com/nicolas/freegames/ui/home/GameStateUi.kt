package com.nicolas.freegames.ui.home

import androidx.compose.runtime.Stable
import com.nicolas.freegames.model.GameDomain

@Stable
data class GameStateUi(
    val isLoading : Boolean = false,
    val data : List<GameDomain> = listOf(),
    val hasError : Boolean = false,
    val loaded : Boolean = false
)

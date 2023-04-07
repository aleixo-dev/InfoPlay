package com.nicolas.freegames.ui.home

import androidx.compose.runtime.Stable
import com.nicolas.freegames.models.domain.ModelGame

@Stable
data class GameStateUi(
    val isLoading : Boolean = false,
    val data : List<ModelGame> = listOf(),
    val hasError : Boolean = false,
    val loaded : Boolean = false
)

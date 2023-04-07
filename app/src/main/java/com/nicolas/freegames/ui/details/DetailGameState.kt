package com.nicolas.freegames.ui.details

import com.nicolas.freegames.models.domain.DetailGame

data class DetailGameState(
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val data : DetailGame? = DetailGame()
)
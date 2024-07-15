package com.nicolas.freegames.ui.details

import com.nicolas.freegames.model.DetailGameDomain

data class DetailGameState(
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val data : DetailGameDomain? = DetailGameDomain()
)
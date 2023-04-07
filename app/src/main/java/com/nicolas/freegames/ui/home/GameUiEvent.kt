package com.nicolas.freegames.ui.home

sealed class GameUiEvent {
    data class FetchCategory(val category: String) : GameUiEvent()
    object FetchAllGames : GameUiEvent()
}
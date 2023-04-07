package com.nicolas.freegames.ui.details

sealed class DetailGameEvent {
    data class Refresh(val gameId : String) : DetailGameEvent()
}
package com.nicolas.freegames.models.domain

data class DetailGame(
    val title: String? = null,
    val status: String? = null,
    val shortDescription: String? = null,
    val description : String? = null,
    val gameUrl: String? = null,
    val genre: String? = null,
    val platform: String? = null,
    val publisher: String? = null,
    val developer: String? = null,
    val dateGame: String? = null,
    val systemRequirements: SystemRequirements? =
        SystemRequirements("", "", "", "", ""),
    val screenshotsGame: List<ScreenshotsGame>? = listOf()
)
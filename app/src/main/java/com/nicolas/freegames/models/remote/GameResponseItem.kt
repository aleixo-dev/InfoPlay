package com.nicolas.freegames.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponseItem(
    @SerialName("developer")
    val developer: String,
    @SerialName("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @SerialName("game_url")
    val gameUrl: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("id")
    val id: Int,
    @SerialName("platform")
    val platform: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String
)
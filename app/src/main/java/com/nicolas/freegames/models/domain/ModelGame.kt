package com.nicolas.freegames.models.domain

data class ModelGame(
    var id: String? = null,
    var title: String? = null,
    var genre: String? = null,
    var description: String? = null,
    var platform: String? = null,
    var thumbnail: String? = null,
    var publisher: String? = null,
    var developer: String? = null
)
package com.nicolas.freegames.data.mapper

import com.nicolas.freegames.data.local.entities.GameEntity
import com.nicolas.freegames.model.DetailGameDomain
import com.nicolas.freegames.model.GameDomain
import com.nicolas.freegames.model.ScreenshotsGame
import com.nicolas.freegames.model.SystemRequirements
import com.nicolas.freegames.data.network.models.NetworkGame
import com.nicolas.freegames.data.network.models.NetworkGameDetail
import com.nicolas.freegames.data.network.models.Screenshot

/** from api to database entity */

fun NetworkGame.asEntity() = GameEntity(
    id = id ?: 0,
    title = title,
    genre = genre,
    description = shortDescription,
    platform = platform,
    thumbnail = thumbnail.replace("\"", " "),
    publisher = publisher,
    developer = developer
)

/** from api to game domain */

fun NetworkGame.toGameDomain() =
    GameDomain(
        id = id.toString(),
        title = title,
        genre = genre,
        description = shortDescription,
        platform = platform,
        thumbnail = thumbnail,
        publisher = publisher,
        developer = developer
    )

/** from database entity to game domain */

fun GameEntity.asExternalModel() = GameDomain(
    id = id.toString(),
    title = title,
    genre = genre,
    description = description,
    platform = platform,
    thumbnail = thumbnail,
    publisher = publisher,
    developer = developer
)

fun Screenshot.toScreenshotGame() = ScreenshotsGame(id = id.toString(), image = image)

/** from api to game detail domain */

fun NetworkGameDetail.toGameDetailDomain() =
    DetailGameDomain(
        title = title,
        status = status,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        dateGame = releaseDate, description = description,
        systemRequirements = SystemRequirements(
            os = minimumSystemRequirements.os,
            processor = minimumSystemRequirements.processor,
            memory = minimumSystemRequirements.memory,
            graphics = minimumSystemRequirements.graphics,
            storage = minimumSystemRequirements.storage
        ),
        screenshotsGame = screenshots.map { it.toScreenshotGame() }
    )


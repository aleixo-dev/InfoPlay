package com.nicolas.freegames.data.network.datasource

import com.nicolas.freegames.data.network.FreeGameService
import com.nicolas.freegames.data.network.models.MinimumSystemRequirements
import com.nicolas.freegames.data.network.models.NetworkGame
import com.nicolas.freegames.data.network.models.NetworkGameDetail
import com.nicolas.freegames.data.network.models.Screenshot
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GameRemoteDataSourceTest {

    private lateinit var gameRemoteDataSource: GameRemoteDataSource
    private var service = mockk<FreeGameService>()

    @Before
    fun setUp() {
        clearAllMocks()
        gameRemoteDataSource = GameRemoteDataSourceImpl(service)
    }

    @Test
    fun `when call get all games should return list of games`() = runBlocking {

        // Given
        val games = listOf(
            NetworkGame(
                developer = "Inno Games",
                freeToGameProfileUrl = "https://www.freetogame.com/minion-masters",
                gameUrl = "https://www.freetogame.com/open/dungeon-defenders-2",
                genre = "Action",
                id = 1,
                platform = "PC",
                publisher = "Inno Games",
                releaseDate = "2020-03-01",
                shortDescription = "Short description",
                thumbnail = "https://www.freetogame.com/g/182/thumbnail.jpg",
                title = "Minion Masters"
            ),
            NetworkGame(
                developer = "Inno Games",
                freeToGameProfileUrl = "https://www.freetogame.com/minion-masters",
                gameUrl = "https://www.freetogame.com/open/dungeon-defenders-2",
                genre = "Action",
                id = 1,
                platform = "PC",
                publisher = "Inno Games",
                releaseDate = "2020-03-01",
                shortDescription = "Short description",
                thumbnail = "https://www.freetogame.com/g/182/thumbnail.jpg",
                title = "Minion Masters"
            )
        )

        // When
        coEvery { gameRemoteDataSource.getAllGames() } returns games
        val result = gameRemoteDataSource.getAllGames()

        // Then
        assertEquals(games, result)
        coVerify { service.getAllGames() }
    }

    @Test
    fun `when call get game by id should return list of games`() = runBlocking {

        //Given
        val gamesCategory = listOf(
            NetworkGame(
                developer = "Inno Games",
                freeToGameProfileUrl = "https://www.freetogame.com/minion-masters",
                gameUrl = "https://www.freetogame.com/open/dungeon-defenders-2",
                genre = "MMORPG",
                id = 1,
                platform = "PC",
                publisher = "Inno Games",
                releaseDate = "2020-03-01",
                shortDescription = "Short description",
                thumbnail = "https://www.freetogame.com/g/182/thumbnail.jpg",
                title = "Minion Masters"
            ),
            NetworkGame(
                developer = "Inno Games",
                freeToGameProfileUrl = "https://www.freetogame.com/minion-masters",
                gameUrl = "https://www.freetogame.com/open/dungeon-defenders-2",
                genre = "MMORPG",
                id = 1,
                platform = "PC",
                publisher = "Inno Games",
                releaseDate = "2020-03-01",
                shortDescription = "Short description",
                thumbnail = "https://www.freetogame.com/g/182/thumbnail.jpg",
                title = "Minion Masters"
            )
        )

        // When
        coEvery { gameRemoteDataSource.getGamePerCategory("mmorpg") } returns gamesCategory
        val result = gameRemoteDataSource.getGamePerCategory("mmorpg")

        // Then
        assertEquals(gamesCategory, result)
        coVerify { service.getGamePerCategory("mmorpg") }
    }

    @Test
    fun `when call get game detail should return a game`() = runBlocking {

        // Given
        val gameDetail = NetworkGameDetail(
            id = 475,
            title = "Genshin Impact",
            thumbnail = "https://www.freetogame.com/g/475/thumbnail.jpg",
            status = "Live",
            shortDescription = "f you’ve been looking for a game to scratch that open-world action RPG",
            description = "If you’ve been looking for a game to scratch that open-world ARPG itch, one with perhaps a bit of Asian flair, then you’re going to want to check out miHoYo’s Genshin Impact.",
            gameUrl = "https://www.freetogame.com/open/genshin-impact",
            genre = "Action RPG",
            platform = "Windows",
            publisher = "miHoYo",
            developer = "miHoYo",
            releaseDate = "2020-09-28",
            freetogameProfileUrl = "https://www.freetogame.com/genshin-impact",
            minimumSystemRequirements = MinimumSystemRequirements(
                graphics = "NVIDIA GeForce GTX 1060 6GB or better",
                memory = "16 GB",
                os = "Windows 7 SP1 64-bit",
                processor = "Intel Core i7 or equivalent",
                storage = "30 GB"
            ),
            screenshots = listOf(
                Screenshot(
                    1191,
                    image = "https://www.freetogame.com/g/475/genshin-impact-1.jpg"
                )
            )
        )

        coEvery { gameRemoteDataSource.getGameDetail("475") } returns gameDetail
        val result = gameRemoteDataSource.getGameDetail("475")

        assertEquals(gameDetail, result)
        coVerify { gameRemoteDataSource.getGameDetail("475") }
    }
}
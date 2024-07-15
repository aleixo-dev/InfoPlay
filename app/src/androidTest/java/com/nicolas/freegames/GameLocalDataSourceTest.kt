package com.nicolas.freegames

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.nicolas.freegames.data.local.AppDatabase
import com.nicolas.freegames.data.local.dao.ModelGameDao
import com.nicolas.freegames.data.local.entities.GameEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameLocalDataSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ModelGameDao

    @Before
    fun setUp() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.modelGameDao()

    }

    @Test
    fun writeUserAndReadInList() = runBlocking {

        val entities = listOf(
            GameEntity(
                1,
                "dummy title",
                "mmorpg dummy",
                "some dummy description",
                "windows",
                "dummy.png",
                "dummy lta",
                "dummy dev",
            ), GameEntity(
                2,
                "dummy title",
                "mmorpg dummy",
                "some dummy description",
                "windows",
                "dummy.png",
                "dummy lta",
                "dummy dev",
            )
        )

        dao.insert(entities)
        val user = dao.getData()
        assertThat(user[0].title, equalTo(entities[0].title))

    }

    @After
    fun closeDatabase() {
        database.close()
    }
}
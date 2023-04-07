package com.nicolas.freegames.utils

import androidx.compose.ui.graphics.Color
import com.nicolas.freegames.R
import com.nicolas.freegames.ui.theme.QuartzColor

const val EMPTY = ""

data class Category(val gameCategory: GameCategory, val color: Color = QuartzColor)

enum class GameCategory(val title: String, val query: String, val icon: Int) {
    TOWER_DEFENSE(title = "T-DEFENSE", query = "tower-defense", icon = R.drawable.fps_icon),
    MMORPG(title = "MMORPG", query = "mmorpg", icon = R.drawable.mmorpg_icon),
    SHOOTER(title = "SHOOTER", query = "shooter", icon = R.drawable.shooter_icon),
    PVP(title = "PVP", query = "pvp", icon = R.drawable.pvp_icon),
    MMOFPS(title = "MMOFPS", query = "mmofps", icon = R.drawable.mmofps_icon),
    ANIME(title = "ANIME", query = "anime", icon = R.drawable.mmorpg_icon),
    FANTASY(title = "FANTASY", query = "fantasy", icon = R.drawable.fantasy_icon),
    PIXEL(title = "PIXEL", query = "pixel", icon = R.drawable.pixel_moustache_svgrepo_com)
}

object MockData {

    fun provideCategories() = listOf(
        Category(
            gameCategory = GameCategory.TOWER_DEFENSE,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.MMORPG,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.SHOOTER,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.PVP,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.MMOFPS,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.ANIME,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.FANTASY,
            color = QuartzColor
        ),
        Category(
            gameCategory = GameCategory.PIXEL,
            color = QuartzColor
        )
    )
}

private fun String.checkIfUrlIsNotNull(): String {
    return this.ifEmpty { EMPTY }
}
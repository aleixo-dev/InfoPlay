package com.nicolas.freegames.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.nicolas.freegames.model.GameDomain
import com.nicolas.freegames.ui.navigation.Route

@Composable
fun CardGame(game: GameDomain, navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.clickable {
            navController.navigate(route = "${Route.DETAILS.name}/${game.id}")
        }
    ) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(game.thumbnail)
                .size(Size.ORIGINAL)
                .build()
        )

        Image(
            painter = painter, modifier = Modifier
                .size(width = 145.dp, height = 85.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "thumbnail_game"
        )

        Text(
            text = game.title ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp),
            style = MaterialTheme.typography.subtitle2
        )
    }
}
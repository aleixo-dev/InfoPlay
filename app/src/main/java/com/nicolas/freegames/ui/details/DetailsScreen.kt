package com.nicolas.freegames.ui.details

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.nicolas.freegames.models.domain.DetailGame
import com.nicolas.freegames.navigation.Screen
import com.nicolas.freegames.ui.theme.QuartzColor
import com.nicolas.freegames.utils.AdView
import com.nicolas.freegames.utils.ErrorModal
import com.nicolas.freegames.utils.OpenExternalLink

@Composable
fun DetailScreen(
    gameId: String?,
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) { gameId?.let { detailViewModel.loadGameData(it) } }
    val state by detailViewModel.uiState.collectAsState()
    val rememberScroll = rememberScrollState()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        ActionTop(
            onBackScreen = {
                navController.popBackStack(
                    Screen.HOME.name,
                    inclusive = false,
                    saveState = false
                )
            },
            onInfo = {
                state.data?.gameUrl?.let {
                    OpenExternalLink.open(it, launcher = launcher)
                }
            })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScroll)
                .padding(bottom = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = DarkGray)
            } else if (state.isError) {
                ErrorModal(retry = { detailViewModel.onEvent(DetailGameEvent.Refresh(gameId.toString())) })
            } else {
                state.data?.let { MovieSection(state = it) }
            }
        }
    }
}


@Composable
fun MovieSection(state: DetailGame) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = state.title.toString(), modifier = Modifier
                    .fillMaxWidth(), maxLines = 1, overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold, color = DarkGray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = state.platform.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .background(color = QuartzColor, shape = RoundedCornerShape(10.dp))
                    .padding(5.dp), color = White
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = state.genre.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .background(color = QuartzColor, shape = RoundedCornerShape(10.dp))
                    .padding(5.dp), color = White
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
        ScreenshotsSlider(detailGame = state)
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = state.shortDescription ?: "",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = 10.dp), color = DarkGray
        )
        Spacer(modifier = Modifier.size(10.dp))
        AdView(modifier = Modifier.fillMaxWidth()) // show admob
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = state.description.toString(),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .padding(horizontal = 10.dp), color = DarkGray
        )
        Spacer(modifier = Modifier.size(10.dp))
        AdView(modifier = Modifier.fillMaxWidth())
        SystemRequirementsGame(gameDetailGame = state)
    }
}

@Composable
fun SystemRequirementsGame(gameDetailGame: DetailGame) {
    AnimatedVisibility(visible = gameDetailGame.systemRequirements?.os != null) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "Requisitos minimo do sistema",
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.h6, color = DarkGray
            )
            Spacer(modifier = Modifier.size(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .background(color = DarkGray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Text(
                    text = gameDetailGame.systemRequirements?.os ?: "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2, color = White
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = gameDetailGame.systemRequirements?.processor ?: "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2, color = White
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = gameDetailGame.systemRequirements?.memory ?: "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2, color = White
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = gameDetailGame.systemRequirements?.graphics ?: "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2, color = White
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = gameDetailGame.systemRequirements?.storage ?: "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2, color = White
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenshotsSlider(detailGame: DetailGame) {
    val pagerState = rememberPagerState()

    detailGame.screenshotsGame?.size?.let {
        HorizontalPager(
            pageCount = it,
            state = pagerState, contentPadding = PaddingValues(horizontal = 10.dp)
        ) { images ->
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(detailGame.screenshotsGame[images].image)
                    .crossfade(true)
                    .build(),
                loading = { CircularProgressIndicator(color = QuartzColor) },
                contentDescription = ""
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                .clip(RoundedCornerShape(5.dp)),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(it) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}

@Composable
fun ActionTop(modifier: Modifier = Modifier, onBackScreen: () -> Unit, onInfo: () -> Unit) {

    val currentContext = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(color = QuartzColor, shape = RoundedCornerShape(5.dp))
                .clickable { onBackScreen.invoke() }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "icon_arrow_back",
                tint = White
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(color = QuartzColor, shape = RoundedCornerShape(5.dp))
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "icon_info_back",
                tint = White, modifier = Modifier.clickable { onInfo.invoke() }
            )
        }
    }
}
package com.nicolas.freegames.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.nicolas.freegames.R
import com.nicolas.freegames.ui.theme.CrayolaColor
import com.nicolas.freegames.ui.theme.QuartzColor

@Composable
fun ErrorModal(retry: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .padding(40.dp)
    ) {
        Loader()
        Text(
            text = "oops! houve um erro",
            style = MaterialTheme.typography.subtitle2,
            color = Color.DarkGray
        )
        Button(
            onClick = { retry.invoke() },
            modifier = Modifier.padding(top = 10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White, backgroundColor = Color.DarkGray
            )
        ) {
            Text(text = "Recarregar".uppercase(), style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        modifier = Modifier.size(100.dp),
        composition = composition,
        progress = { progress },
    )
}

@Preview(showBackground = true)
@Composable
fun ShowError() {
    ErrorModal {

    }
}


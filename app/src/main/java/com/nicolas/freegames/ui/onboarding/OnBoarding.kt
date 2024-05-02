package com.nicolas.freegames.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolas.freegames.ui.theme.QuartzColor

@Composable
fun OnBoarding(onClickButton: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Ooooooolá🥳",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4, color = Color.DarkGray
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Feliz em ter você conosco\nInfo Play encontre seu estilo de jogo",
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = com.nicolas.freegames.R.drawable.onboarding_image),
            contentDescription = "", alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.size(100.dp))
        Text(
            text = "Aqui você encontra os melhores jogos gratuitos do mundo bem na palma da sua mão totalmente gratuito!",
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End), horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(color = QuartzColor, shape = RoundedCornerShape(10.dp))
                    .clickable { onClickButton.invoke() }
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "", tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnBoardingPreview() {
    OnBoarding {

    }
}
package com.nicolas.freegames.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import com.nicolas.freegames.ui.theme.QuartzColor
import com.nicolas.freegames.utils.UserStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

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
            text = "Oooooooooooooooolá",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5, color = Color.DarkGray
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "bem vindo ao free games!",
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = com.nicolas.freegames.R.drawable.onboarding_image),
            contentDescription = "", alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = "um applicativo onde você pode ver os\nmelhores jogos gratis!",
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2,
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
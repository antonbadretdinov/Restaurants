package com.example.restaurants.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurants.R

@Composable
fun NoConnectionScreen(
    padding: PaddingValues,
    onReloadClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .aspectRatio(1f),
            ) {

                Icon(
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_no_internet),
                    contentDescription = stringResource(id = R.string.no_internet_description),
                    tint = MaterialTheme.colorScheme.primary
                )

            }

            Text(
                text = stringResource(id = R.string.no_connection),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 2.dp
                )
            )

            Text(
                text = stringResource(id = R.string.try_again_text),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 2.dp,
                    bottom = 24.dp
                )
            )

            Button(onClick = {
                onReloadClicked()
            }) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    }
}
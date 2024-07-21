package com.example.restaurants.ui.screens.details

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.restaurants.R

@Composable
fun PhotoItem(
    photo: String
) {
    val context = LocalContext.current

    AsyncImage(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(26.dp))
            .size(
                width = 400.dp,
                height = 250.dp
            ),
        model = ImageRequest
            .Builder(context)
            .data(photo)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.photo_of_organization_in_details),
        contentScale = ContentScale.Crop
    )
}
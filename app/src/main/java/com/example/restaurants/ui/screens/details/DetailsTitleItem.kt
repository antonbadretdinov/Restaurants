package com.example.restaurants.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.restaurants.R
import com.example.restaurants.ui.theme.background2
import com.example.restaurants.ui.theme.background4

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DetailsTitleItem(
    title: String,
    rate: Double,
    averageCheck: Double? = null,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = background2,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                val (titleText, iconButton) = createRefs()
                Text(
                    modifier = Modifier
                        .constrainAs(titleText) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(iconButton.start)
                            width = Dimension.fillToConstraints
                        },
                    textAlign = TextAlign.Start,
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    modifier = Modifier
                        .constrainAs(iconButton) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                        .size(26.dp),
                    onClick = {
                        onFavoriteClick()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(
                            id = if (isFavorite)
                                R.drawable.ic_fav
                            else
                                R.drawable.ic_not_fav
                        ),
                        contentDescription = stringResource(
                            id = R.string.icon_for_favorite_organization
                        ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        items(rate.toInt()) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rating),
                                contentDescription = stringResource(id = R.string.icon_for_rating_star),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        items(5 - rate.toInt()) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_no_rating),
                                contentDescription = stringResource(id = R.string.icon_for_not_rating_star),
                                tint = background4
                            )
                        }
                    }

                    Text(
                        text = rate.toString(),
                        modifier = Modifier
                            .padding(start = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    averageCheck?.let {
                        Text(
                            text = stringResource(R.string.euro_symbol),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = averageCheck.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}
package com.example.restaurants.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.restaurants.R
import com.example.restaurants.helpers.DEFAULT_ID_LOADING
import com.example.restaurants.helpers.utils.hasInternetConnection
import com.example.restaurants.ui.screens.NoConnectionScreen
import com.example.restaurants.ui.viewModels.DetailsViewModel
import kotlin.random.Random

@Composable
fun DetailsScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    id: String,
    onBackClick: () -> Unit
) {

    val context = LocalContext.current

    val detailsUIState = detailsViewModel.detailsFlow.collectAsStateWithLifecycle()

    var reloadPageState by remember {
        mutableStateOf(false)
    }

    var isFavoredState by rememberSaveable {
        mutableStateOf(false)
    }

    val randomAverageCheck by rememberSaveable {
        mutableStateOf((1..5).map {
            Random.nextInt(from = 1, until = 100)
        })
    }

    LaunchedEffect(reloadPageState) {
        if (reloadPageState) {
            reloadPageState = false
            detailsViewModel.getDetailsById(id.toInt())
        }
    }

    LaunchedEffect(detailsUIState.value) {
        isFavoredState = detailsUIState.value.isFavorite
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                detailsViewModel.getDetailsById(id.toInt())
            } else if (event == Lifecycle.Event.ON_STOP) {
                if (isFavoredState) {
                    detailsViewModel.addToFavoriteById(detailsUIState.value.id)
                } else {
                    detailsViewModel.deleteFromFavoriteById(detailsUIState.value.id)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp)
                    .safeContentPadding(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.33f)
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(
                                R.string.icon_for_back_button
                            )
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.title_restaurant),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .fillMaxWidth(0.5f),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { topAppBarPadding ->
        if (!context.hasInternetConnection()) {
            NoConnectionScreen(
                padding = topAppBarPadding
            ) {
                reloadPageState = true
            }
        } else {
            if (detailsUIState.value.id != DEFAULT_ID_LOADING) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(topAppBarPadding)
                        .verticalScroll(rememberScrollState())
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    LazyRow {
                        items(
                            detailsUIState.value.photos
                        ) { photo ->
                            PhotoItem(
                                photo = photo
                            )
                        }
                    }

                    DetailsTitleItem(
                        title = detailsUIState.value.name,
                        rate = detailsUIState.value.rate,
                        averageCheck = if (detailsUIState.value.averageCheck.isEmpty())
                            randomAverageCheck.average()
                        else
                            detailsUIState.value.averageCheck.average(),
                        isFavorite = isFavoredState,
                        onFavoriteClick = {
                            isFavoredState = !isFavoredState
                        }
                    )

                    DetailsDescriptionItem(
                        description = if (detailsUIState.value.detailedInfo.isNotEmpty() &&
                            detailsUIState.value.detailedInfo.isNotBlank()
                        )
                            detailsUIState.value.detailedInfo
                        else
                            stringResource(id = R.string.example_text_description)
                    )
                }

            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(topAppBarPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

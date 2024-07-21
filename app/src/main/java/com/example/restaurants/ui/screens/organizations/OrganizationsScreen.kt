package com.example.restaurants.ui.screens.organizations

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.restaurants.helpers.utils.hasInternetConnection
import com.example.restaurants.ui.screens.NoConnectionScreen
import com.example.restaurants.ui.theme.background2
import com.example.restaurants.ui.viewModels.OrganizationsViewModel
import kotlin.random.Random

@Composable
fun OrganizationsScreen(
    onItemClick: (id: Int) -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    organizationsViewModel: OrganizationsViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    var reloadPageState by remember {
        mutableStateOf(false)
    }

    var favoriteIds by remember { mutableStateOf(setOf<Int>()) }

    var favoriteCount by remember {
        mutableIntStateOf(0)
    }

    val organizationsUIState =
        organizationsViewModel.organizationsFlow.collectAsStateWithLifecycle()

    LaunchedEffect(organizationsUIState.value.organizationsData) {
        favoriteIds = organizationsUIState.value.organizationsData
            .filter { it.isFavorite }
            .map { it.id }
            .toSet()
        favoriteCount = favoriteIds.size
    }

    var isFavoriteVisibleNowState by rememberSaveable {
        mutableStateOf(false)
    }

    Log.e("MyTag", "Count:$favoriteCount")
    Log.e("MyTag", "ids: $favoriteIds")

    LaunchedEffect(reloadPageState) {
        if (reloadPageState) {
            reloadPageState = false
            organizationsViewModel.getOrganizationsList()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                organizationsViewModel.getOrganizationsList()
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
                Spacer(modifier = Modifier.fillMaxWidth(0.33f))

                Text(
                    text = stringResource(R.string.title_restaurants),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .fillMaxWidth(0.5f),
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(contentAlignment = Alignment.Center) {
                        IconButton(
                            modifier = Modifier.size(26.dp),
                            onClick = {
                                isFavoriteVisibleNowState = !isFavoriteVisibleNowState
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(
                                    id = if (isFavoriteVisibleNowState)
                                        R.drawable.ic_fav
                                    else
                                        R.drawable.ic_not_fav
                                ),
                                contentDescription = stringResource(id = R.string.icon_for_favorite_organization),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        Text(
                            text = favoriteCount.toString(),
                            style = MaterialTheme.typography.labelLarge,
                            color = if (isFavoriteVisibleNowState)
                                background2
                            else
                                MaterialTheme.colorScheme.primary
                        )
                    }

                }
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
            if (organizationsUIState.value.organizationsData.isNotEmpty()) {

                val filteredOrganizations = if (isFavoriteVisibleNowState) {
                    organizationsUIState.value.organizationsData
                        .filter { favoriteIds.contains(it.id) }
                } else {
                    organizationsUIState.value.organizationsData
                }

                val randomAverage = remember {
                    (1..organizationsUIState.value.organizationsData.size).map {
                        Random.nextInt(from = 50, until = 1000)
                    }
                }

                if (isFavoriteVisibleNowState && filteredOrganizations.isEmpty()) {
                    NoFavoriteScreen(padding = topAppBarPadding)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(topAppBarPadding)
                ) {
                    items(
                        filteredOrganizations
                    ) { organizationItem ->

                        var cuisinesStr = ""

                        organizationItem.cuisines.forEachIndexed { index, itemStr ->
                            cuisinesStr += itemStr
                            if (index != organizationItem.cuisines.size - 1) {
                                cuisinesStr += ", "
                            }
                        }

                        OrganizationListItem(
                            title = organizationItem.name,
                            rating = organizationItem.rate,
                            cuisines = cuisinesStr,
                            averageCheck = if (organizationItem.averageCheck.isEmpty())
                                randomAverage[organizationsUIState.value.organizationsData.indexOf(
                                    organizationItem
                                )]
                            else
                                organizationItem.averageCheck.average().toInt(),
                            photo = organizationItem.photo,
                            onClick = {
                                onItemClick(
                                    organizationItem.id
                                )
                            },
                            isFavorite = favoriteIds.contains(organizationItem.id),
                            onFavoriteClick = {
                                if (favoriteIds.contains(organizationItem.id)) {
                                    favoriteIds -= organizationItem.id
                                    favoriteCount -= 1
                                    organizationsViewModel.deleteFromFavoriteById(organizationItem.id)
                                } else {
                                    favoriteIds += organizationItem.id
                                    favoriteCount += 1
                                    organizationsViewModel.addToFavoriteById(organizationItem.id)
                                }
                            }
                        )
                    }
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
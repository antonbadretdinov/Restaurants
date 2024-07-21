package com.example.restaurants.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurants.helpers.DETAILS_ROUTE
import com.example.restaurants.helpers.ID_KEY
import com.example.restaurants.ui.screens.details.DetailsScreen
import com.example.restaurants.ui.screens.organizations.OrganizationsScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Organizations.route
    ) {
        composable(
            route = Screens.Organizations.route
        ) {
            OrganizationsScreen(
                onItemClick = { id ->
                    navController.navigate(
                        "$DETAILS_ROUTE/$id"
                    )
                }
            )
        }

        composable(
            route = Screens.Details.route,
        ) {
            val id = it.arguments?.getString(ID_KEY)
            id?.let { orgId ->
                DetailsScreen(
                    id = orgId,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }

    }
}
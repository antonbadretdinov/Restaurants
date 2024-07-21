package com.example.restaurants.ui.navigation

import com.example.restaurants.helpers.DETAILS_ROUTE
import com.example.restaurants.helpers.ID_KEY
import com.example.restaurants.helpers.ORGANIZATIONS_ROUTE

sealed class Screens(
    val route: String
) {
    data object Organizations : Screens(ORGANIZATIONS_ROUTE)
    data object Details : Screens("$DETAILS_ROUTE/{$ID_KEY}")
}
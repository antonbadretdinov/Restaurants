package com.example.restaurants.data.remote.repository

import com.example.restaurants.data.remote.retrofit.DetailsApi
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val detailsApi: DetailsApi
) {
    suspend fun getDetailsById(id: Int) = detailsApi.getDetailsById(id)

    suspend fun addToFavoriteById(id: Int) = detailsApi.addToFavoriteById(id)

    suspend fun deleteFromFavoriteById(id: Int) = detailsApi.deleteFromFavoriteById(id)
}
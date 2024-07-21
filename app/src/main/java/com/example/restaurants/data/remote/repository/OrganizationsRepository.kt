package com.example.restaurants.data.remote.repository

import com.example.restaurants.data.remote.retrofit.OrganizationsApi
import javax.inject.Inject

class OrganizationsRepository @Inject constructor(
    private val organizationsApi: OrganizationsApi
) {
    suspend fun getOrganizationsList() = organizationsApi.getOrganizationsList()

    suspend fun addToFavoriteById(id: Int) = organizationsApi.addToFavoriteById(id)

    suspend fun deleteFromFavoriteById(id: Int) = organizationsApi.deleteFromFavoriteById(id)
}